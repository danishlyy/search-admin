package com.search.admin.adapter.controller;

import com.search.admin.adapter.request.Customer;
import com.search.admin.adapter.request.IndexAddRequestVO;
import com.search.admin.adapter.request.IndexMappingPropertiesRequestVO;
import com.search.admin.adapter.request.Trade;
import com.search.admin.infra.base.Result;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private SearchAdminClient searchAdminClient;

    /**
     * simple field
     * @return
     */
    @PostMapping(value = "/v1/test/create/mapping")
    public Result<String> testPutMapping(){
        List<IndexAddRequestVO> list = new ArrayList<>();
        IndexAddRequestVO vo = new IndexAddRequestVO();
        List<IndexMappingPropertiesRequestVO> fields = new ArrayList<>();
        IndexMappingPropertiesRequestVO f1 = new IndexMappingPropertiesRequestVO();
        f1.setFieldName("productCode");
        f1.setFieldType("text");
        f1.setAnalyzeFlag("0");
//        f1.setAnalyzeType("standard");
        fields.add(f1);
        IndexMappingPropertiesRequestVO f2 = new IndexMappingPropertiesRequestVO();
        f2.setFieldName("productName");
        f2.setFieldType("text");
        f2.setAnalyzeFlag("0");
//        f2.setAnalyzeType("standard");
        fields.add(f2);
//
//        IndexMappingPropertiesRequestVO f3 = new IndexMappingPropertiesRequestVO();
//        f3.setFieldName("customerInfo");
//        f3.setFieldType("object");
//        List<IndexMappingPropertiesRequestVO> customerFields = new ArrayList<>();
//        IndexMappingPropertiesRequestVO customer1 = new IndexMappingPropertiesRequestVO();
//        customer1.setFieldName("userName");
//        customer1.setFieldType("text");
//        IndexMappingPropertiesRequestVO customer2 = new IndexMappingPropertiesRequestVO();
//        customer2.setFieldName("phone");
//        customer2.setFieldType("text");
//        customerFields.add(customer2);
//        customerFields.add(customer1);
//        f3.setObjectFields(customerFields);
//        fields.add(f3);
        vo.setFields(fields);
        list.add(vo);
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("product_info");
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards","1")
                .put("index.number_of_replicas","1").build());
        List<IndexMappingPropertiesRequestVO> fields1 = list.get(0).getFields();
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject("properties");
                {
                    for (IndexMappingPropertiesRequestVO item:fields1){

                            if ("object".equals(item.getFieldType())){

                            }
                            builder.startObject(item.getFieldName());
                            {
                                builder.field("type", item.getFieldType());
//                                builder.field("analyzer",item.getAnalyzeType());
                            }
                            builder.endObject();

                    }
                }
                builder.endObject();
            }
            builder.endObject();
            createIndexRequest.mapping(builder);
            createIndexRequest.mapping(builder);
            client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(null);
    }

    @PostMapping(value = "/v1/create/object/mapping")
    public Result<String> createObjectMapping(){
        Trade trade = new Trade();
        Customer customer = new Customer();
        trade.setCustomer(customer);
        trade.setOrderNo("1234");
        trade.setOrderDate("20220101");
        customer.setUserName("Mike");
        customer.setPhone("1234");
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("trade_info");
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards","1")
                .put("index.number_of_replicas","1").build());
        try {
            Map<String,Trade> m = new HashMap<>();
            m.put("properties",trade);
            createIndexRequest.mapping(JacksonUtil.toJsonString(m), XContentType.JSON);
            CreateIndexResponse indexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success(null);
    }
}

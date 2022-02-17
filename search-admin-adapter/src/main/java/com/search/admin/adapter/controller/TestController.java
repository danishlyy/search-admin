package com.search.admin.adapter.controller;

import com.search.admin.adapter.request.Customer;
import com.search.admin.adapter.request.IndexAddRequestVO;
import com.search.admin.adapter.request.IndexMappingPropertiesRequestVO;
import com.search.admin.adapter.request.Trade;
import com.search.admin.infra.base.Result;
import com.search.admin.infra.config.SearchAdminClient;
import com.search.admin.infra.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

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
        f1.setAnalyzeType("standard");
        fields.add(f1);
        IndexMappingPropertiesRequestVO f2 = new IndexMappingPropertiesRequestVO();
        f2.setFieldName("productName");
        f2.setFieldType("text");
        f2.setAnalyzeFlag("0");
//        f2.setAnalyzeType("standard");
        fields.add(f2);
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
                            builder.startObject(item.getFieldName());
                            {
                                builder.field("type", item.getFieldType());
                                if (StringUtils.isNotBlank(item.getAnalyzeType())){
                                    builder.field("analyzer",item.getAnalyzeType());
                                }
                            }
                            builder.endObject();

                    }
                }
                builder.endObject();
            }
            builder.endObject();
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

    @GetMapping(value = "/v1/get/index/mappings")
    public Result<String> getIndexMapping() throws IOException {
        GetMappingsRequest getMappingsRequest = new GetMappingsRequest();
        getMappingsRequest.indices("twitter");
        RestHighLevelClient client = searchAdminClient.elasticsearchClient();
        GetMappingsResponse mapping = client.indices().getMapping(getMappingsRequest, RequestOptions.DEFAULT);
        Map<String, MappingMetadata> mappings = mapping.mappings();
//        {"twitter":{"sourceAsMap":{"properties":{"productCode":{"type":"keyword"},"email":{"type":"keyword"}}}}}
        log.info(JacksonUtil.toJsonString(mappings));
        Set<Map.Entry<String, MappingMetadata>> entries = mappings.entrySet();
        for (Map.Entry<String, MappingMetadata> m:entries){
//            key:twitter,type:{},source:{}
            log.info("key:{},type:{},source:{}",m.getKey());
            Map<String, Object> sourceAsMap = m.getValue().getSourceAsMap();
            for (Map.Entry<String, Object> m1:sourceAsMap.entrySet()){
//                key:properties,value:{productCode={type=keyword}, email={type=keyword}}
                log.info("key:{},value:{}",m1.getKey(),m1.getValue());
                String s = JacksonUtil.toJsonString(m1.getValue());
                log.info("json结果：{}",s);
            }
        }
        return Result.success(null);
    }
}

package com.search.admin.infra.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SearchAdminClient {

    @Autowired
    private SearchServiceConfig searchServiceConfig;

    @Bean
    public RestHighLevelClient elasticsearchClient(){
        try {
            String[] hosts = searchServiceConfig.getHosts();
            log.info("es hosts:{}",hosts);
            int len = hosts.length;
            HttpHost[] httpHosts = new HttpHost[len];

            for (int i=0;i<len;i++){
                String[] split = StringUtils.split(hosts[i],":");
                httpHosts[i] = new HttpHost(split[0],Integer.parseInt(split[1]));
            }
            RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(httpHosts));
            return restHighLevelClient;
        }catch (Exception e){
            log.error("init elasticsearch failed",e);
            throw new RuntimeException();
        }

    }
}

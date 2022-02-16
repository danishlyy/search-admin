package com.search.admin.adapter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.search.admin")
@MapperScan("com.search.admin.infra.storage.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class SearchAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchAdminApplication.class);
    }
}

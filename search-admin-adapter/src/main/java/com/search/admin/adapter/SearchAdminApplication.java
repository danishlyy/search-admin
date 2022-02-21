package com.search.admin.adapter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = "com.search.admin")
@MapperScan("com.search.admin.infra.storage.mapper")
@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class SearchAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchAdminApplication.class);
    }
}

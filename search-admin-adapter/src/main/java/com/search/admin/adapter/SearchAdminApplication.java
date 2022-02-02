package com.search.admin.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.search.admin")
@SpringBootApplication
public class SearchAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchAdminApplication.class);
    }
}

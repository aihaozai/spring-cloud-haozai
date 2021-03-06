package com.spring.cloud.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import spring.cloud.base.core.annotation.EnableXxlJob;

@EnableXxlJob
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudElasticsearchApplication.class, args);
    }

}

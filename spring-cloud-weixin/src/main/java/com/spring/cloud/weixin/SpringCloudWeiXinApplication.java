package com.spring.cloud.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import spring.cloud.base.resource.starter.config.EnableGatewayResourceServer;

@EnableDiscoveryClient
@EnableGatewayResourceServer
@SpringBootApplication
public class SpringCloudWeiXinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudWeiXinApplication.class, args);
    }

}

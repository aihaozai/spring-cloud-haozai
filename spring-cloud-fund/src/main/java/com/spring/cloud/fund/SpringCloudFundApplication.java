package com.spring.cloud.fund;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import spring.cloud.base.core.annotation.EnableXxlJob;

@EnableXxlJob
@MapperScan("com.spring.cloud.fund.**.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudFundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFundApplication.class, args);
    }

}

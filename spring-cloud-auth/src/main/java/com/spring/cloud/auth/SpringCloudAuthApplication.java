package com.spring.cloud.auth;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EnableDistributedTransaction
@MapperScan("com.spring.cloud.auth.**.mapper")
@SpringBootApplication
public class SpringCloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAuthApplication.class, args);
    }

}

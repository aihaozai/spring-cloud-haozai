package com.spring.cloud.fund;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import spring.cloud.base.core.annotation.EnableXxlJob;
import spring.cloud.base.resource.starter.config.EnableGatewayResourceServer;

@EnableXxlJob
@EnableDistributedTransaction
@MapperScan("com.spring.cloud.fund.**.mapper")
@EnableDiscoveryClient
@EnableGatewayResourceServer
@SpringBootApplication
public class SpringCloudFundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFundApplication.class, args);
    }

}

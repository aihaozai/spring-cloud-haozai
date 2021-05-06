package com.spring.cloud.fund;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.spring.cloud.fund.**.mapper")
@SpringBootApplication
public class SpringCloudFundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFundApplication.class, args);
    }

}

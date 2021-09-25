package com.spring.cloud.tx.manage;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableTransactionManagerServer
@SpringBootApplication
public class SpringCloudTxManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTxManageApplication.class, args);
    }

}

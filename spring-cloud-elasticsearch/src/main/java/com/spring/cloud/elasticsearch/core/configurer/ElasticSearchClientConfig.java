package com.spring.cloud.elasticsearch.core.configurer;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haozai
 * @description 客户端配置
 * @date 2021/4/20  22:20
 */
@Configuration
public class ElasticSearchClientConfig {

    @Bean
    public RestClientBuilder restClientBuilder() {
        return RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"));
    }

    @Bean
    public RestHighLevelClient resthighLevelClient() {
        return new RestHighLevelClient(restClientBuilder());
    }

}

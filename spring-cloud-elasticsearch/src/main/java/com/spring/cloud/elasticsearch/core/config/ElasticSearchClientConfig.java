package com.spring.cloud.elasticsearch.core.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

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

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);
        requestFactory.setReadTimeout(10000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}

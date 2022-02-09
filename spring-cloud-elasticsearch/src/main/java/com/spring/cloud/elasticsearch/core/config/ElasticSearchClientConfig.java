package com.spring.cloud.elasticsearch.core.config;

import lombok.AllArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
public class ElasticSearchClientConfig {

    private final ElasticSearchProperties elasticSearchProperties;

    @Bean
    public RestClientBuilder restClientBuilder() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(elasticSearchProperties.getHost(), elasticSearchProperties.getPort(), elasticSearchProperties.getScheme()));

        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(elasticSearchProperties.getConnectTimeOut());
            requestConfigBuilder.setSocketTimeout(elasticSearchProperties.getSocketTimeOut());
            requestConfigBuilder.setConnectionRequestTimeout(elasticSearchProperties.getConnectionRequestTimeOut());
            return requestConfigBuilder;
        });

        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(elasticSearchProperties.getMaxConnectTotal());
            httpClientBuilder.setMaxConnPerRoute(elasticSearchProperties.getMaxConnectPerRoute());
            return httpClientBuilder;
        });
        return builder;
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

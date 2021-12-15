package com.spring.cloud.elasticsearch.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author haozai
 * @description elasticsearch属性
 * @date 2021/12/15  11:06
 */
@Data
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchProperties {

    private String host;

    private Integer port;

    private String scheme;

    private Integer connectTimeOut;

    private Integer socketTimeOut;

    private Integer connectionRequestTimeOut;

    private Integer maxConnectTotal;

    private Integer maxConnectPerRoute;
}

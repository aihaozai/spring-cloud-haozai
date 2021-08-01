package com.spring.cloud.fund.core.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/8/1  20:50
 */
public interface ResourceServerConfigurer {
    void configure(ResourceServerSecurityConfigurer var1) throws Exception;

    void configure(HttpSecurity var1) throws Exception;
}


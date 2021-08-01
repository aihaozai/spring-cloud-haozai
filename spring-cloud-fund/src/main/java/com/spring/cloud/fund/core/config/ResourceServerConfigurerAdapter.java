package com.spring.cloud.fund.core.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/8/1  20:49
 */
public class ResourceServerConfigurerAdapter {
    public ResourceServerConfigurerAdapter() {
    }

    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    public void configure(HttpSecurity http) throws Exception {
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().anyRequest()).authenticated();
    }
}

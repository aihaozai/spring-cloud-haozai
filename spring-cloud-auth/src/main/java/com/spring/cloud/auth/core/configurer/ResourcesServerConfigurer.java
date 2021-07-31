package com.spring.cloud.auth.core.configurer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author haozai
 * @description 资源服务器配置
 * @date 2021/3/24  23:10
 */

@Configuration
@EnableResourceServer
@AllArgsConstructor
public class ResourcesServerConfigurer extends ResourceServerConfigurerAdapter {

    @Qualifier("redisTokenStore")
    private final TokenStore redisTokenStore;

    /**
     * @description 资源id可再次手动设置任意字符串，如果不设置，则需要在数据oauth_client_details中的resource_ids填写固定值"oauth2-resource"
     * @param resources 资源
     * @throws Exception e
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("auth_resource");
        resources.tokenStore(redisTokenStore);
    }

    /**
     * @description 配置需要拦截的资源
     * @param http http
     * @throws Exception e
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http .authorizeRequests()
                .antMatchers("/oauth/**","/login","/health","/keyPair/**","/res/**","/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}

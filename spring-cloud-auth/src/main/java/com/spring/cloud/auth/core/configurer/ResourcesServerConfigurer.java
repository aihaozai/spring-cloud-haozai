package com.spring.cloud.auth.core.configurer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author haozai
 * @description 资源服务器配置
 * @date 2021/3/24  23:10
 */

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourcesServerConfigurer extends ResourceServerConfigurerAdapter {
    @Qualifier("tokenServices")
    private final DefaultTokenServices tokenServices;

    @Qualifier("redisTokenStore")
    private final TokenStore redisTokenStore;

    /**
     * @description 资源id可再次手动设置任意字符串，如果不设置，则需要在数据oauth_client_details中的resource_ids填写固定值"oauth2-resource"
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices);
        resources.tokenStore(redisTokenStore);
    }

    /**
     * @description 配置需要拦截的资源，这里可扩展的比较多，自由发挥
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http .authorizeRequests()
                .antMatchers("/oauth/**","/login","/health","/keyPair/**","/res/**","/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}

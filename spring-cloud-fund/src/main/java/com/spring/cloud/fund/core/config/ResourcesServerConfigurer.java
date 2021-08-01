package com.spring.cloud.fund.core.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author haozai
 * @description 资源服务器配置
 * @date 2021/3/24  23:10
 */

@Configuration
@ResourceServer
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourcesServerConfigurer extends ResourceServerConfigurerAdapter {


    /**
     * redis
     */
    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * token存储
     */
    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * tokenService
     */
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    /**
     * 认证管理器
     */
    private OAuth2AuthenticationManager oAuth2AuthenticationManager() {
        OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
        oAuth2AuthenticationManager.setTokenServices(defaultTokenServices());
        return oAuth2AuthenticationManager;
    }

    @Bean
    @Primary
    public OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter() {
        OAuth2AuthenticationProcessingFilter OAuth2AuthenticationProcessingFilter = new OAuth2AuthenticationProcessingFilter();
        OAuth2AuthenticationProcessingFilter.setAuthenticationManager(oAuth2AuthenticationManager());
        return OAuth2AuthenticationProcessingFilter;
    }

    /**
     * @description 资源服务配置
     * @param resources 资源
     * @throws Exception e
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("auth_resource");
    }

    /**
     * @description 配置需要拦截的资源
     * @param http http
     * @throws Exception e
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("fundReal/getFundRealDataAnon","/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

}

package com.spring.cloud.fund.core.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author haozai
 * @description 资源服务器配置
 * @date 2021/3/24  23:10
 */

@Configuration
@EnableResourceServer
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
    @Bean(name = "redisTokenStore")
    @Primary
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * @description 资源服务配置
     * @param resources 资源
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("auth_resource");
        resources.tokenStore(tokenStore());
    }

    /**
     * @description 配置需要拦截的资源
     * @param http http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("fundReal/getFundRealDataAnon").anonymous();
        http.authorizeRequests()
                .anyRequest().authenticated();
    }

}

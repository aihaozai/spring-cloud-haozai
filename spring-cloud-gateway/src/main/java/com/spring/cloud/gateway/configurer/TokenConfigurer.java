package com.spring.cloud.gateway.configurer;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/4/5  13:29
 */
@Configuration
@AllArgsConstructor
public class TokenConfigurer {
    /**
     * redis
     */
    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * token存储
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

}

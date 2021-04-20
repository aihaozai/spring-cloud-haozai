package spring.cloud.base.security.starter.configurer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.KeyPair;

/**
 * @author haozai
 * @description Token配置
 * @date 2021/3/24  22:30
 */
@Configuration
@AllArgsConstructor
public class TokenConfigurer {

    /**
     * redis
     */
    @Qualifier("connectionFactory")
    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * token增强器
     * @return
     */
    @Bean(name ="systemJwtTokenEnhancer")
    public TokenEnhancer systemJwtTokenEnhancer() {
        return new SystemJwtTokenEnhancer();
    }

    /**
     *
     * @return TokenStore
     */
    @Bean(name = "systemJwtAccessTokenConverter")
    public TokenStore systemJwtTokenStore() {
        return new JwtTokenStore(systemJwtTokenConverter());
    }

    /**
     * token存储
     */
    @Bean(name = "redisTokenStore")
    @Primary
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * @return DefaultTokenServices
     */
    @Bean(name = "tokenServices")
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    /**
     * 非对称性加密
     * token生成处理：指定签名
     * @return JwtAccessTokenConverter
     */
    @Bean(name = "systemJwtTokenConverter")
    public JwtAccessTokenConverter systemJwtTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new SystemJwtAccessTokenConverter();
        accessTokenConverter.setKeyPair(rsaKeyPair());
        return accessTokenConverter;
    }

    /**
     * 密钥
     * @return
     */
    @Bean
    public KeyPair rsaKeyPair() {
        //从classpath下的证书中获取秘钥对
        return new KeyStoreKeyFactory(new ClassPathResource("haozai-jwt.jks"), "haozai".toCharArray())
                .getKeyPair("haozai-jwt");
    }
}

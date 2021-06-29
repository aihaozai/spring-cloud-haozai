package com.spring.cloud.gateway.authentication;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.net.HttpHeaders;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author haozai
 * @description 认证转换器
 * @date 2021/4/5  11:55
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationConverter implements ServerAuthenticationConverter {

    private final TokenStore tokenStore ;

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        String authorization = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(authorization)){
            return Mono.empty();
        }
        String token =authorization.replace(OAuth2AccessToken.BEARER_TYPE + " ", "");
        log.info("当前Token的值: {}",token);
        OAuth2Authentication oAuth2Authentication = this.tokenStore.readAuthentication(token);
        if(ObjectUtil.isEmpty(oAuth2Authentication)){
            return Mono.empty();
        }
        return Mono.just(oAuth2Authentication);
    }
}


package com.spring.cloud.gateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author haozai
 * @description 认证入口
 * @date 2021/4/5  17:40
 */
@Slf4j
@Component
public class AuthenticationEntryPointHandler implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        log.info("认证入口---请求链接:{},原因:{}", serverWebExchange.getRequest().getPath(), e.getMessage());
        String url = "http://localhost";
        serverWebExchange.getResponse().setStatusCode(HttpStatus.SEE_OTHER);
        serverWebExchange.getResponse().getHeaders().set(HttpHeaders.LOCATION, url);
        return serverWebExchange.getResponse().setComplete();
    }
}

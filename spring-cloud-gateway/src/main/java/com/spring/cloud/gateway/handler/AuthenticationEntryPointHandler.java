package com.spring.cloud.gateway.handler;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author haozai
 * @description 认证入口
 * @date 2021/4/5  17:40
 */
@Slf4j
@Component
public class AuthenticationEntryPointHandler implements ServerAuthenticationEntryPoint {

    private static final JSONObject RESPONSE = new JSONObject();

    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        log.error("认证失败---请求链接:{},原因:{}", serverWebExchange.getRequest().getPath(), e.getMessage());
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

        RESPONSE.put("msg", e.getMessage());
        DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
        return serverWebExchange.getResponse().writeWith(Flux.just(bufferFactory.wrap(RESPONSE.toString().getBytes())));
    }
}

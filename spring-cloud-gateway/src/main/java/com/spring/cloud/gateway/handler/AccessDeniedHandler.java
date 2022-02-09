package com.spring.cloud.gateway.handler;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author haozai
 * @description 拒绝通过处理
 * @date 2021/4/5  17:28
 */
@Slf4j
@Component
public class AccessDeniedHandler implements ServerAccessDeniedHandler {
    private static final JSONObject RESPONSE = new JSONObject();

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        log.error("拒绝通过---请求链接:{},原因:{}", serverWebExchange.getRequest().getPath(), e.getMessage());

        RESPONSE.put("msg", e.getMessage());
        DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
        return serverWebExchange.getResponse().writeWith(Flux.just(bufferFactory.wrap(RESPONSE.toString().getBytes())));

    }
}

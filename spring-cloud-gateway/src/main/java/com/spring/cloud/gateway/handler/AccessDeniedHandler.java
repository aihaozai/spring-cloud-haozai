package com.spring.cloud.gateway.handler;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
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
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        log.info("拒绝通过---请求链接:{},原因:{}", serverWebExchange.getRequest().getPath(), e.getMessage());
        JSONObject object = new JSONObject();
        object.putOnce("message", e.getMessage());
        DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
        return serverWebExchange.getResponse().writeWith(Flux.just(bufferFactory.wrap(object.toString().getBytes())));

    }
}

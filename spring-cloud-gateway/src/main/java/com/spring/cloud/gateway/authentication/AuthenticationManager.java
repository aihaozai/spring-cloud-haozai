package com.spring.cloud.gateway.authentication;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author haozai
 * @description 认证管理器
 * @date 2021/4/5  13:38
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if(authentication instanceof OAuth2Authentication){
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            String clientId = oAuth2Authentication.getOAuth2Request().getClientId();
            oAuth2Authentication.setAuthenticated(false);
            log.info("client_id: {}",clientId);
            return Mono.just(oAuth2Authentication);
        }
        Object principal = authentication.getPrincipal();
        log.info("用户信息: {}",  new JSONObject(principal));
        return Mono.just(authentication);
    }
}

package com.spring.cloud.auth.core.hander;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import spring.cloud.base.core.result.Result;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author haozai
 * @description 退出成功处理器
 * @date 2021/9/15 13:42
 */

@AllArgsConstructor
public class SystemLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisTokenStore redisTokenStore;

    private final ObjectMapper objectMapper;

    private final MessageSource message;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isNotBlank(authorization)){
            String token =authorization.replace(OAuth2AccessToken.BEARER_TYPE + " ", "");
            OAuth2AccessToken oAuth2AccessToken = this.redisTokenStore.readAccessToken(token);
            if(ObjectUtils.isNotEmpty(oAuth2AccessToken)){
                redisTokenStore.removeAccessToken(oAuth2AccessToken);
                OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
                redisTokenStore.removeRefreshToken(oAuth2RefreshToken);
                redisTokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
            }
        }
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(Result.ok(this.message.getMessage("AuthenticationProvider.logout",null, Locale.getDefault()))));
    }
}

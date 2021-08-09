package com.spring.cloud.auth.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author haozai
 * @description 源码自定义登录接口
 * @date 2021/8/7  16:26
 */
@RequestMapping("/oauth")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final BasicAuthenticationConverter authenticationConverter = new BasicAuthenticationConverter();
    private final TokenEndpoint tokenEndpoint;

    @GetMapping("/weixin/token")
    public ResponseEntity<OAuth2AccessToken> postAccessToken(HttpServletRequest request, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {

        UsernamePasswordAuthenticationToken token = this.authenticationConverter.convert(request);
        UsernamePasswordAuthenticationToken successToken = new UsernamePasswordAuthenticationToken(token.getPrincipal(), token.getCredentials(), AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        successToken.setDetails(token.getDetails());
        return tokenEndpoint.postAccessToken(successToken,parameters);

    }

}

package com.spring.cloud.auth.core.controller;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.endpoint.AbstractEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

/**
 * @author haozai
 * @description 源码自定义登录接口
 * @date 2021/8/7  16:26
 */
@RequestMapping("/oauth")
@RestController
public class LoginController extends TokenEndpoint {
    private final BasicAuthenticationConverter authenticationConverter = new BasicAuthenticationConverter();
    private OAuth2RequestValidator oAuth2RequestValidator = new DefaultOAuth2RequestValidator();

    public LoginController(){
        super.setTokenGranter(super.getTokenGranter());
    }
    @GetMapping("/weixin/token")
    public ResponseEntity<OAuth2AccessToken> postAccessToken(HttpServletRequest request, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        Authentication authRequest = this.authenticationConverter.convert(request);
        String clientId = "";
        ClientDetails authenticatedClient = this.getClientDetailsService().loadClientByClientId(clientId);
        TokenRequest tokenRequest = this.getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient);
        if (clientId != null && !clientId.equals("") && !clientId.equals(tokenRequest.getClientId())) {
            throw new InvalidClientException("Given client ID does not match authenticated client");
        } else {
            if (authenticatedClient != null) {
                this.oAuth2RequestValidator.validateScope(tokenRequest, authenticatedClient);
            }

            if (!StringUtils.hasText(tokenRequest.getGrantType())) {
                throw new InvalidRequestException("Missing grant type");
            } else if (tokenRequest.getGrantType().equals("implicit")) {
                throw new InvalidGrantException("Implicit grant type not supported from token endpoint");
            } else {
                if (this.isAuthCodeRequest(parameters) && !tokenRequest.getScope().isEmpty()) {
                    this.logger.debug("Clearing scope of incoming token request");
                    tokenRequest.setScope(Collections.emptySet());
                }

                if (this.isRefreshTokenRequest(parameters)) {
                    tokenRequest.setScope(OAuth2Utils.parseParameterList((String)parameters.get("scope")));
                }

                OAuth2AccessToken token = this.getTokenGranter().grant(tokenRequest.getGrantType(), tokenRequest);
                if (token == null) {
                    throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
                } else {
                    return this.getResponse(token);
                }
            }
        }
    }

    private ResponseEntity<OAuth2AccessToken> getResponse(OAuth2AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        headers.set("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity(accessToken, headers, HttpStatus.OK);
    }

    private boolean isRefreshTokenRequest(Map<String, String> parameters) {
        return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
    }

    private boolean isAuthCodeRequest(Map<String, String> parameters) {
        return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
    }
}

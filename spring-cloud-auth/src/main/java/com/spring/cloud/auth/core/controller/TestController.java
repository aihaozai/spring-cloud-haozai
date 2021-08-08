package com.spring.cloud.auth.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springCloud
 * @author: haozai
 * @date: 2020-04-12 14:11
 **/
@Api("testController")
@RefreshScope
@RequestMapping("/res")
@RestController
public class TestController {
    //OrRequestMatcher
   // BasicAuthenticationFilter
   // TokenRequest
   // ResourceOwnerPasswordTokenGranter
    @Value("${me}")
    private String me;
    @ApiOperation(value = "me", httpMethod = "GET")
    @GetMapping("/me")
    public String me(){
        return this.me;
    }


}

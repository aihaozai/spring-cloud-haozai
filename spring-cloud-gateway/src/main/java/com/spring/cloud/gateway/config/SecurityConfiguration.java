package com.spring.cloud.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.spring.cloud.gateway.authentication.AuthenticationConverter;
import com.spring.cloud.gateway.authentication.AuthenticationManager;
import com.spring.cloud.gateway.handler.AccessDeniedHandler;
import com.spring.cloud.gateway.handler.AuthenticationEntryPointHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * @author haozai
 * @description 安全配置
 * @date 2021/3/27  22:59
 */
@AllArgsConstructor
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private final AuthenticationManager authenticationManager;

    private final AuthenticationConverter authenticationConverter;

    private final AccessDeniedHandler accessDeniedHandler;

    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;

    private final Set<String> permitAll = new HashSet<>();

    private static final String MAX_AGE = "18000L";

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        permitAll.add("/api/auth/oauth/**");
        permitAll.add("/api/*/v2/api-docs");
        permitAll.add("/api/fund/fundReal/getFundRealDataAnon");
        permitAll.add("/swagger-ui/**");
        permitAll.add("/swagger-resources/**");
        permitAll.add("/favicon.ico");
        permitAll.add("/doc.html");
        permitAll.add("/webjars/**");
        permitAll.add("/xxl-job-admin/api/registry");
        permitAll.add("/api/weixin/wx/user/*/login");
        http.authorizeExchange()
                //白名单配置
                .pathMatchers(ArrayUtil.toArray(permitAll,String.class)).permitAll()
                .anyExchange().authenticated()
                .and().exceptionHandling()
                //处理未授权
                .accessDeniedHandler(accessDeniedHandler)
                //处理未认证
                .authenticationEntryPoint(authenticationEntryPointHandler)
                .and().csrf().disable()
                .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterAt(corsFilter(), SecurityWebFiltersOrder.CORS);
        return http.build();
    }

    /**
     * 身份认证过滤器
     * @return AuthenticationWebFilter
     */

    private AuthenticationWebFilter authenticationWebFilter(){
        AuthenticationWebFilter authenticationWebFilter= new AuthenticationWebFilter(authenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(authenticationConverter);
        return authenticationWebFilter;
    }

    /**
     * 跨域过滤器
     * @return WebFilter
     */
    private WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders requestHeaders = request.getHeaders();
                ServerHttpResponse response = ctx.getResponse();
                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
                HttpHeaders headers = response.getHeaders();
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
                headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
                if (requestMethod != null) {
                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
                }
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }
}

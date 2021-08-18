package com.spring.cloud.auth.core.config;


import com.spring.cloud.auth.core.service.UserServiceDetailImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haozai
 * @description 授权服务器
 * @date 2021/3/24  22:22
 */

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器authenticationManager 开启密码授权
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 验证身份
     */
    private final UserServiceDetailImpl userServiceDetail;

    /**
     * 数据源
     */
    private final DataSource dataSource;

    /**
     * redisTokenStore
     */
    @Qualifier("redisTokenStore")
    private final TokenStore redisTokenStore ;

    /**
     * token增强器
     */
    @Qualifier("systemJwtTokenEnhancer")
    private final TokenEnhancer systemJwtTokenEnhancer ;

    /**
     * accessToken增强器
     */
    @Qualifier("systemJwtAccessTokenConverter")
    private final  JwtAccessTokenConverter systemJwtAccessTokenConverter;

    /**
     * 客户端信息，来源与DB
     * @return JdbcClientDetailsService
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 将自定授权保存数据库
     * @return ApprovalStore
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * 将授权码保存数据库
     * @return AuthorizationCodeServices
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 配置客户端详情服务（ClientDetailsService）
     * 客户端详情信息在这里进行初始化
     * 通过数据库来存储调取详情信息
     * 配置客户但详情从数据库读取，默认手动添加到oauth2客户端表中的数据
     * @return ClientDetailsService
     */
    @Bean
    public ClientDetailsService jdbcClientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer){
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 从数据库获取客户端
     * @param clients 客户端
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetails());
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints){
        endpoints.tokenStore(redisTokenStore)
                //配置将授权码存放在oauth_code变中，默认存在内存中
                .authorizationCodeServices(authorizationCodeServices())
                //配置审批存储oauth_approvals，存储用户审批过程，在一个月时间内不用再次审批
                .approvalStore(approvalStore())
                .authenticationManager(authenticationManager)
                //配置用户信息的服务
                .userDetailsService(userServiceDetail)
                //支持刷新令牌
                .reuseRefreshTokens(true)
                //支持GET  POST  请求获取token;
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(systemJwtTokenEnhancer);
        //配置JWT的内容增强器 将自定义Enhancer加入EnhancerChain的delegates数组中
        enhancerChain.setTokenEnhancers(enhancers);
        endpoints.tokenEnhancer(enhancerChain)
                .accessTokenConverter(systemJwtAccessTokenConverter);
    }
}
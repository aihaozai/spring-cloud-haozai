package spring.cloud.base.resource.starter.config;

import org.springframework.http.MediaType;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author haozai
 * @date 2021/8/1  20:41
 */

public final class ResourceServerSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
    private AccessDeniedHandler accessDeniedHandler = new OAuth2AccessDeniedHandler();
    private OAuth2AuthenticationProcessingFilter resourcesServerFilter;
    private AuthenticationManager authenticationManager;
    private AuthenticationEventPublisher eventPublisher = null;
    private ResourceServerTokenServices resourceTokenServices;
    private TokenStore tokenStore = new InMemoryTokenStore();
    private String resourceId = "oauth2-resource";
    private SecurityExpressionHandler<FilterInvocation> expressionHandler = new OAuth2WebSecurityExpressionHandler();
    private TokenExtractor tokenExtractor;
    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;
    private boolean stateless = true;

    public ResourceServerSecurityConfigurer() {
        this.resourceId(this.resourceId);
    }

    private ClientDetailsService clientDetails() {
        return (ClientDetailsService)((HttpSecurity)this.getBuilder()).getSharedObject(ClientDetailsService.class);
    }

    public TokenStore getTokenStore() {
        return this.tokenStore;
    }

    public ResourceServerSecurityConfigurer stateless(boolean stateless) {
        this.stateless = stateless;
        return this;
    }

    public ResourceServerSecurityConfigurer authenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        return this;
    }

    public ResourceServerSecurityConfigurer accessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
        return this;
    }

    public ResourceServerSecurityConfigurer tokenStore(TokenStore tokenStore) {
        Assert.state(tokenStore != null, "TokenStore cannot be null");
        this.tokenStore = tokenStore;
        return this;
    }

    public ResourceServerSecurityConfigurer eventPublisher(AuthenticationEventPublisher eventPublisher) {
        Assert.state(eventPublisher != null, "AuthenticationEventPublisher cannot be null");
        this.eventPublisher = eventPublisher;
        return this;
    }

    public ResourceServerSecurityConfigurer expressionHandler(SecurityExpressionHandler<FilterInvocation> expressionHandler) {
        Assert.state(expressionHandler != null, "SecurityExpressionHandler cannot be null");
        this.expressionHandler = expressionHandler;
        return this;
    }

    public ResourceServerSecurityConfigurer tokenExtractor(TokenExtractor tokenExtractor) {
        Assert.state(tokenExtractor != null, "TokenExtractor cannot be null");
        this.tokenExtractor = tokenExtractor;
        return this;
    }

    public ResourceServerSecurityConfigurer authenticationDetailsSource(AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        Assert.state(authenticationDetailsSource != null, "AuthenticationDetailsSource cannot be null");
        this.authenticationDetailsSource = authenticationDetailsSource;
        return this;
    }

    public ResourceServerSecurityConfigurer authenticationManager(AuthenticationManager authenticationManager) {
        Assert.state(authenticationManager != null, "AuthenticationManager cannot be null");
        this.authenticationManager = authenticationManager;
        return this;
    }

    public ResourceServerSecurityConfigurer tokenServices(ResourceServerTokenServices tokenServices) {
        Assert.state(tokenServices != null, "ResourceServerTokenServices cannot be null");
        this.resourceTokenServices = tokenServices;
        return this;
    }

    @Override
    public void init(HttpSecurity http) throws Exception {
        this.registerDefaultAuthenticationEntryPoint(http);
    }

    private void registerDefaultAuthenticationEntryPoint(HttpSecurity http) {
        ExceptionHandlingConfigurer<HttpSecurity> exceptionHandling = (ExceptionHandlingConfigurer)http.getConfigurer(ExceptionHandlingConfigurer.class);
        if (exceptionHandling != null) {
            ContentNegotiationStrategy contentNegotiationStrategy = (ContentNegotiationStrategy)http.getSharedObject(ContentNegotiationStrategy.class);
            if (contentNegotiationStrategy == null) {
                contentNegotiationStrategy = new HeaderContentNegotiationStrategy();
            }

            MediaTypeRequestMatcher preferredMatcher = new MediaTypeRequestMatcher((ContentNegotiationStrategy)contentNegotiationStrategy, new MediaType[]{MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.MULTIPART_FORM_DATA, MediaType.TEXT_XML});
            preferredMatcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
            exceptionHandling.defaultAuthenticationEntryPointFor((AuthenticationEntryPoint)this.postProcess(this.authenticationEntryPoint), preferredMatcher);
        }
    }

    public ResourceServerSecurityConfigurer resourceId(String resourceId) {
        this.resourceId = resourceId;
        if (this.authenticationEntryPoint instanceof OAuth2AuthenticationEntryPoint) {
            ((OAuth2AuthenticationEntryPoint)this.authenticationEntryPoint).setRealmName(resourceId);
        }

        return this;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager oauthAuthenticationManager = this.oauthAuthenticationManager(http);
        this.resourcesServerFilter = new OAuth2AuthenticationProcessingFilter();
        this.resourcesServerFilter.setAuthenticationEntryPoint(this.authenticationEntryPoint);
        this.resourcesServerFilter.setAuthenticationManager(oauthAuthenticationManager);
        if (this.eventPublisher != null) {
            this.resourcesServerFilter.setAuthenticationEventPublisher(this.eventPublisher);
        }

        if (this.tokenExtractor != null) {
            this.resourcesServerFilter.setTokenExtractor(this.tokenExtractor);
        }

        if (this.authenticationDetailsSource != null) {
            this.resourcesServerFilter.setAuthenticationDetailsSource(this.authenticationDetailsSource);
        }

        this.resourcesServerFilter = (OAuth2AuthenticationProcessingFilter)this.postProcess(this.resourcesServerFilter);
        this.resourcesServerFilter.setStateless(this.stateless);
        ((HttpSecurity)http.authorizeRequests().expressionHandler(this.expressionHandler).and()).addFilterBefore(this.resourcesServerFilter, AbstractPreAuthenticatedProcessingFilter.class).exceptionHandling().accessDeniedHandler(this.accessDeniedHandler).authenticationEntryPoint(this.authenticationEntryPoint);
    }

    private AuthenticationManager oauthAuthenticationManager(HttpSecurity http) {
        OAuth2AuthenticationManager oauthAuthenticationManager = new OAuth2AuthenticationManager();
        if (this.authenticationManager != null) {
            if (!(this.authenticationManager instanceof OAuth2AuthenticationManager)) {
                return this.authenticationManager;
            }

            oauthAuthenticationManager = (OAuth2AuthenticationManager)this.authenticationManager;
        }

        oauthAuthenticationManager.setResourceId(this.resourceId);
        oauthAuthenticationManager.setTokenServices(this.resourceTokenServices(http));
        oauthAuthenticationManager.setClientDetailsService(this.clientDetails());
        return oauthAuthenticationManager;
    }

    private ResourceServerTokenServices resourceTokenServices(HttpSecurity http) {
        this.tokenServices(http);
        return this.resourceTokenServices;
    }

    private ResourceServerTokenServices tokenServices(HttpSecurity http) {
        if (this.resourceTokenServices != null) {
            return this.resourceTokenServices;
        } else {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setTokenStore(this.tokenStore());
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setClientDetailsService(this.clientDetails());
            this.resourceTokenServices = tokenServices;
            return tokenServices;
        }
    }

    private TokenStore tokenStore() {
        Assert.state(this.tokenStore != null, "TokenStore cannot be null");
        return this.tokenStore;
    }

    public AccessDeniedHandler getAccessDeniedHandler() {
        return this.accessDeniedHandler;
    }
}
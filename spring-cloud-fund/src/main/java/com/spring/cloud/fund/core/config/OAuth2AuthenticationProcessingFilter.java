package com.spring.cloud.fund.core.config;

import com.spring.cloud.fund.core.util.GsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE;
import static org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE;

/**
 * @author haozai
 * @date 2021/8/1  10:26
 */
public class OAuth2AuthenticationProcessingFilter implements Filter, InitializingBean {
    private final Log logger = LogFactory.getLog(OAuth2AuthenticationProcessingFilter.class);
    private AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
    private AuthenticationManager authenticationManager;
    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new OAuth2AuthenticationDetailsSource();
    private TokenExtractor tokenExtractor = new BearerTokenExtractor();
    private AuthenticationEventPublisher eventPublisher = new OAuth2AuthenticationProcessingFilter.NullEventPublisher();
    private boolean stateless = true;

    public OAuth2AuthenticationProcessingFilter() {
    }

    public void setStateless(boolean stateless) {
        this.stateless = stateless;
    }

    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setTokenExtractor(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }

    public void setAuthenticationEventPublisher(AuthenticationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void setAuthenticationDetailsSource(AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.state(this.authenticationManager != null, "AuthenticationManager is required");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean debug = logger.isDebugEnabled();
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        try {
            Authentication authentication = this.tokenExtractor.extract(request);
            if (authentication == null) {
                if (this.stateless && this.isAuthenticated()) {
                    if (debug) {
                        logger.debug("Clearing security context.");
                    }

                    SecurityContextHolder.clearContext();
                }

                if (debug) {
                    logger.debug("No token in request, will continue chain.");
                }
            } else {

                request.setAttribute(ACCESS_TOKEN_VALUE, authentication.getPrincipal());
                if (authentication instanceof AbstractAuthenticationToken) {
                    AbstractAuthenticationToken needsDetails = (AbstractAuthenticationToken)authentication;
                    needsDetails.setDetails(this.authenticationDetailsSource.buildDetails(request));
                }
                authentication = GsonUtil.deserializeAuthentication(request.getHeader("oAuth2Authentication"));
                if (debug) {
                    logger.debug("Authentication success: " + authentication);
                }

                this.eventPublisher.publishAuthenticationSuccess(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (OAuth2Exception var9) {
            SecurityContextHolder.clearContext();
            if (debug) {
                logger.debug("Authentication request failed: " + var9);
            }

            this.eventPublisher.publishAuthenticationFailure(new BadCredentialsException(var9.getMessage(), var9), new PreAuthenticatedAuthenticationToken("access-token", "N/A"));
            this.authenticationEntryPoint.commence(request, response, new InsufficientAuthenticationException(var9.getMessage(), var9));
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    private static final class NullEventPublisher implements AuthenticationEventPublisher {
        private NullEventPublisher() {
        }

        @Override
        public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        }

        @Override
        public void publishAuthenticationSuccess(Authentication authentication) {
        }
    }

    public static void main(String[] args) {
        System.out.println(ACCESS_TOKEN_VALUE);
    }
}

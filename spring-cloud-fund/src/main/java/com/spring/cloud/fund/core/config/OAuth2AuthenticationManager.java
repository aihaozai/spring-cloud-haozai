package com.spring.cloud.fund.core.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @author haozai
 * @date 2021/8/1  10:24
 */
public class OAuth2AuthenticationManager implements AuthenticationManager, InitializingBean {

    private ResourceServerTokenServices tokenServices;
    private ClientDetailsService clientDetailsService;
    private String resourceId;

    public OAuth2AuthenticationManager() {
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    public void setTokenServices(ResourceServerTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.state(this.tokenServices != null, "TokenServices are required");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null) {
            throw new InvalidTokenException("Invalid token (token not found)");
        } else {
            String token = (String)authentication.getPrincipal();
            OAuth2Authentication auth = this.tokenServices.loadAuthentication(token);
            if (auth == null) {
                throw new InvalidTokenException("Invalid token: " + token);
            } else {
                Collection<String> resourceIds = auth.getOAuth2Request().getResourceIds();
                if (this.resourceId != null && resourceIds != null && !resourceIds.isEmpty() && !resourceIds.contains(this.resourceId)) {
                    throw new OAuth2AccessDeniedException("Invalid token does not contain resource id (" + this.resourceId + ")");
                } else {
                    this.checkClientDetails(auth);
                    if (authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
                        if (!details.equals(auth.getDetails())) {
                            details.setDecodedDetails(auth.getDetails());
                        }
                    }

                    auth.setDetails(authentication.getDetails());
                    auth.setAuthenticated(true);
                    return auth;
                }
            }
        }
    }

    private void checkClientDetails(OAuth2Authentication auth) {
        if (this.clientDetailsService != null) {
            ClientDetails client;
            try {
                client = this.clientDetailsService.loadClientByClientId(auth.getOAuth2Request().getClientId());
            } catch (ClientRegistrationException var6) {
                throw new OAuth2AccessDeniedException("Invalid token contains invalid client id");
            }

            Set<String> allowed = client.getScope();
            Iterator var4 = auth.getOAuth2Request().getScope().iterator();

            while(var4.hasNext()) {
                String scope = (String)var4.next();
                if (!allowed.contains(scope)) {
                    throw new OAuth2AccessDeniedException("Invalid token contains disallowed scope (" + scope + ") for this client");
                }
            }
        }

    }
}

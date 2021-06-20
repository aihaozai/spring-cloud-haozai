package spring.cloud.base.security.starter.configurer;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haozai
 * @description token增强器
 * @date 2021/3/24  22:40
 */
public class SystemJwtTokenEnhancer implements TokenEnhancer {
    /**
     *  自定义一些token属性
     * @param accessToken
     * @param authentication
     * @return OAuth2AccessToken
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String userName = authentication.getUserAuthentication().getName();
        User user = (User) authentication.getUserAuthentication().getPrincipal();
        final Map<String, Object> additionalInformation = new HashMap<>(1);
        additionalInformation.put("userName", userName);
        additionalInformation.put("address", "广东省");
        additionalInformation.put("expireAt",accessToken.getExpiration());
        DefaultOAuth2AccessToken auth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
        auth2AccessToken.setAdditionalInformation(additionalInformation);
        return auth2AccessToken;
    }
}

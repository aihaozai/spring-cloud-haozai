package spring.cloud.base.resource.starter.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import spring.cloud.base.core.entity.AuthUser;

/**
 * @author haozai
 * @date 2021/8/3  21:50
 */
public class OAuth2ResourceUtil {

    public static OAuth2Authentication getAuthentication() {
        return (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
    }

    public static AuthUser getAuthUser() {
        return GsonUtil.authenticationFromJson(getAuthentication().getUserAuthentication().getPrincipal(),AuthUser.class);
    }

    public static Long getUserId() {
        return getAuthUser().getId();
    }
}

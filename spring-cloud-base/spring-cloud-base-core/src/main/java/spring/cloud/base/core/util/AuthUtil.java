package spring.cloud.base.core.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import spring.cloud.base.core.entity.AuthUser;

/**
 * @author haozai
 * @date 2021/7/31  22:15
 */
public class AuthUtil {

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static AuthUser getAuthUser() {
        return (AuthUser) getAuthentication().getPrincipal();
    }

    public static Long getUserId() {
        return getAuthUser().getId();
    }
}

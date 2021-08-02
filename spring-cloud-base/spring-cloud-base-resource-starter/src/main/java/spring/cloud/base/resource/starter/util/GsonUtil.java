package spring.cloud.base.resource.starter.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author haozai
 * @description Gson工具类
 * @date 2021/8/1  18:59
 */
public class GsonUtil {

    private static final Gson AUTHENTICATION_GSON = new GsonBuilder().registerTypeAdapter(Authentication.class, new InterfaceAdapter<UsernamePasswordAuthenticationToken>())
            .registerTypeAdapter(GrantedAuthority.class, new InterfaceAdapter<SimpleGrantedAuthority>())
            .create();

    public static OAuth2Authentication deserializeOAuth2Authentication(String json){
        return AUTHENTICATION_GSON.fromJson(json,OAuth2Authentication.class);
    }

    public static Authentication deserializeAuthentication(String json){
        return (Authentication)AUTHENTICATION_GSON.fromJson(json,OAuth2Authentication.class);
    }
}

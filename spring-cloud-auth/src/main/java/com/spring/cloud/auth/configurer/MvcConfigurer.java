package com.spring.cloud.auth.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author haozai
 * @description mvc配置
 * @date 2021/3/24  22:40
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
    /**
     *  自定义的登陆页面
     *  自定义的授权页面
     * @param registry 注册
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("login");
       // registry.addViewController("/oauth/confirm_access").setViewName("oauth_approval");
    }
}

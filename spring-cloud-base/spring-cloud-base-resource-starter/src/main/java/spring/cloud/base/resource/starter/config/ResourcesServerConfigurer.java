package spring.cloud.base.resource.starter.config;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import spring.cloud.base.resource.starter.properties.GatewayResourceServiceProperties;

/**
 * @author haozai
 * @description 资源服务器配置
 * @date 2021/3/24  23:10
 */

@AllArgsConstructor
@EnableAutoConfiguration
@EnableConfigurationProperties(GatewayResourceServiceProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourcesServerConfigurer extends ResourceServerConfigurerAdapter {

    private final GatewayResourceServiceProperties properties;

    /**
     * @description 资源服务配置
     * @param resources 资源
     * @throws Exception e
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(properties.getResourceId());
    }

    /**
     * @description 配置需要拦截的资源
     * @param http http
     * @throws Exception e
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(ArrayUtil.toArray(properties.getPermitAll(),String.class)).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

}

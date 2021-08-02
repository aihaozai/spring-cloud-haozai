package spring.cloud.base.resource.starter.config;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * @author haozai
 * @description 网关资源服务器
 * @date 2021/8/1  20:43
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ResourcesServerConfigurer.class,ResourceServerConfiguration.class})
public @interface EnableGatewayResourceServer {
}

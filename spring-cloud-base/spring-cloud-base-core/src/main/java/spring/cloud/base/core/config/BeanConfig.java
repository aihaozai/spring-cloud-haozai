package spring.cloud.base.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author haozai
 * @description 自动配置
 * @date 2021/5/13  23:37
 */
@Configuration
@ComponentScan(basePackages = {"spring.cloud.base.core.bean"})
public class BeanConfig {

}

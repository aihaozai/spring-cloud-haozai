package spring.cloud.base.core.annotation;

import org.springframework.context.annotation.Import;
import spring.cloud.base.core.config.XxlJobConfig;

import java.lang.annotation.*;

/**
 * @author haozai
 * @description xxl-job
 * @date 2021/6/2  21:51
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(XxlJobConfig.class)
public @interface EnableXxlJob {
}

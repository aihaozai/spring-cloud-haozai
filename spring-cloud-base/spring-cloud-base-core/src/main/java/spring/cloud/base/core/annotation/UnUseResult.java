package spring.cloud.base.core.annotation;

import java.lang.annotation.*;

/**
 * @author haozai
 * @description 取消自定义返回结果注解
 * @date 2021/6/2  21:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnUseResult {
}

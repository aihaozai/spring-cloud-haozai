package com.spring.cloud.fund.core.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/8/1  20:43
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ResourceServerConfiguration.class})
public @interface ResourceServer {
}

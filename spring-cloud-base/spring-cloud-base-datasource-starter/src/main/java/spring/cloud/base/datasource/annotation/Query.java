/**
 * Copyright (C) 2018-2021
 * All rights reserved, Designed By www.yixiang.co

 */
package spring.cloud.base.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author haozai
 * @date 2021/4/30  16:07
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    /**
     * 别称
     * @return
     */
    String nickname() default "";

    /**
     * 默认查询类型
     * @return
     */
    Type type() default Type.EQUAL;


    enum Type {
        /**
         * 相等
         */
        EQUAL
    }

}


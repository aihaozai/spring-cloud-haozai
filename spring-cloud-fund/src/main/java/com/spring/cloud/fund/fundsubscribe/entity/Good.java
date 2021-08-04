package com.spring.cloud.fund.fundsubscribe.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.base.IEntity;
import lombok.Data;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/8/4  22:56
 */
@Data
@FluentMybatis
public class Good implements IEntity {
    String id;
}

package com.spring.cloud.fund.fund.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author haozai
 * @description 基金表
 * @date 2021/5/06  15:13
 */
@Data
@TableName("fund")
@Accessors(chain = true)
public class Fund extends BaseEntity implements Serializable  {
    private static final long serialVersionUID = 1L;

    private String fundCode;

    private String fundName;

    private String fundType;

    private String companyCode;

    private String companyName;
}

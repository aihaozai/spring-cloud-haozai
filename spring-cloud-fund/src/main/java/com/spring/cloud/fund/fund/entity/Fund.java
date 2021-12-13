package com.spring.cloud.fund.fund.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import spring.cloud.base.datasource.entity.BaseEntity;

/**
 * @author haozai
 * @description 基金表
 * @date 2021/5/06  15:13
 */
@Data
@TableName("fund")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Fund extends BaseEntity {

    private String fundCode;

    private String fundName;

    private String fundType;

    private String companyCode;

    private String companyName;

}

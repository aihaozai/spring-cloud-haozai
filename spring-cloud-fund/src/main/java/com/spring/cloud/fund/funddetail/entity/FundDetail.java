package com.spring.cloud.fund.funddetail.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author haozai
 * @description 基金详细表
 * @date 2021/6/09  17:37
 */
@Data
@TableName("fund_detail")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class FundDetail extends BaseEntity implements Serializable  {

    private String fundCode;

    private String fundName;

    private String fundType;

    private String companyCode;

    private String companyName;

    /**
     *近一年收益率
     */
    private String oneN;

    /**
     *近6月收益率
     */
    private String sixY;

    /**
     *近三月收益率
     */
    private String threeY;

    /**
     *近一月收益率
     */
    private String oneY;

    /**
     *订阅id
     */
    @TableField(exist = false)
    private String subscribeId;
}

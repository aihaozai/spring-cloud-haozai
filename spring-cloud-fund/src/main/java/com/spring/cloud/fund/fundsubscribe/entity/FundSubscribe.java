package com.spring.cloud.fund.fundsubscribe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import spring.cloud.base.datasource.entity.BaseEntity;

/**
 * @author haozai
 * @description 基金订阅表
 * @date 2021/7/31  15:13
 */
@Data
@Builder
@TableName("fund_subscribe")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class FundSubscribe extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 基金代码
     */
    private String fundCode;

}
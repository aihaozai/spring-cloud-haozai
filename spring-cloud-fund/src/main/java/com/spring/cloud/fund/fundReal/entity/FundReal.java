package com.spring.cloud.fund.fundReal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author haozai
 * @description 基金实时表
 * @date 2021/5/28  15:13
 */
@Data
@TableName("fund_real")
@Accessors(chain = true)
public class FundReal extends BaseEntity {

    /**
     * 基金代码
     */
    private String fundcode;

    /**
     * 基金名称
     */
    private String name;

    /**
     * 净值日期
     */
    private Date jzrq;

    /**
     * 当日净值
     */
    private BigDecimal dwjz;

    /**
     * 估算净值
     */
    private BigDecimal gsz;

    /**
     * 估算涨跌百分比
     */
    private BigDecimal gszzl;

    /**
     * 估值时间
     */
    private Date gztime;
}
package com.spring.cloud.fund.core.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author haozai
 * @date 2021/5/7 14:34
 * @description 基金实时数据
 */
@Data
public class FundRealDataDto {

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

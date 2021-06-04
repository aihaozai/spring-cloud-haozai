package com.spring.cloud.fund.fundReal.entity;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_MINUTE_PATTERN;
import static cn.hutool.core.date.DatePattern.NORM_DATE_PATTERN;


/**
 * @author haozai
 * @description 基金实时表
 * @date 2021/5/28  15:13
 */
@Data
@TableName("fund_real")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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
    @JsonFormat(pattern = NORM_DATETIME_MINUTE_PATTERN ,timezone = "GMT+8")
    private Date gztime;

    /**
     * 爬取日期
     */
    private String searchtime;

    public void setGztime(Date gztime) {
        this.gztime = gztime;
        this.searchtime = DateUtil.format(gztime,NORM_DATE_PATTERN);
    }
}
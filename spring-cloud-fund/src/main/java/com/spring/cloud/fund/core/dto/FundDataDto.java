package com.spring.cloud.fund.core.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author haozai
 * @date 2021/5/7 14:34
 */
@Data
public class FundDataDto {

    private String fundcode;

    private String name;

    private Date jzrq;

    private BigDecimal dwjz;

    private BigDecimal gsz;

    private BigDecimal gszzl;

    private Date gztime;
}

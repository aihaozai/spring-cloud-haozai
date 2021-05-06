package com.spring.cloud.fund.fundCompany.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import spring.cloud.base.datasource.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author haozai
 * @description 基金公司表
 * @date 2021/5/06  15:13
 */
@Data
@TableName("fund_company")
@Accessors(chain = true)
public class FundCompany extends BaseEntity implements Serializable  {
    private static final long serialVersionUID = 1L;

    private String companyCode;

    private String companyName;
}

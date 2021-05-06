package com.spring.cloud.fund.fundCompany.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fundCompany.entity.FundCompany;
import com.spring.cloud.fund.fundCompany.mapper.FundCompanyMapper;
import org.springframework.stereotype.Service;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Service
public class FundCompanyServiceImpl extends ServiceImpl<FundCompanyMapper, FundCompany> implements IFundCompanyService{

}

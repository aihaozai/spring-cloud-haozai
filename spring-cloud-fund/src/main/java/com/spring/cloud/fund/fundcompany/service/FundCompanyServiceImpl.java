package com.spring.cloud.fund.fundcompany.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fundcompany.entity.FundCompany;
import com.spring.cloud.fund.fundcompany.mapper.FundCompanyMapper;
import org.springframework.stereotype.Service;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Service
public class FundCompanyServiceImpl extends ServiceImpl<FundCompanyMapper, FundCompany> implements IFundCompanyService{

}

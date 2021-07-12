package com.spring.cloud.fund.fundCompany.controller;

import com.spring.cloud.fund.core.util.FundSearchUtil;
import com.spring.cloud.fund.fund.service.IFundService;
import com.spring.cloud.fund.fundCompany.entity.FundCompany;
import com.spring.cloud.fund.fundCompany.service.IFundCompanyService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Api("基金公司控制器")
@RequestMapping("/fundCompany")
@RestController
@AllArgsConstructor
public class FundCompanyController{

    private final IFundCompanyService iFundCompanyService;

    private final IFundService iFundService;

    @GetMapping("/addAllFund")
    public void addAllFund() {
        List<FundCompany> fundCompanyList = iFundCompanyService.list();
        for (FundCompany fundCompany: fundCompanyList){
            iFundService.saveBatch(FundSearchUtil.getFundList(fundCompany.getCompanyCode()));
        }
    }
}

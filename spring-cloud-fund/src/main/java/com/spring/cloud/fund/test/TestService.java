package com.spring.cloud.fund.test;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.spring.cloud.fund.fundreal.entity.FundReal;
import com.spring.cloud.fund.fundreal.service.IFundRealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/9/25  19:59
 */
@Service
@RequiredArgsConstructor
public class TestService {

    private final IFundRealService iFundRealService;
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public void test1() {
        FundReal fundReal = new FundReal();
        fundReal.setName("111");
        iFundRealService.save(fundReal);
    }
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public void test2() {
        FundReal fundReal = new FundReal();
        fundReal.setName("222");
        iFundRealService.save(fundReal);
        int ff = 1/0;
    }
}

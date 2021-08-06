package com.spring.cloud.fund.fundreal.controller;

import com.spring.cloud.fund.fund.service.IFundService;
import com.spring.cloud.fund.fundreal.model.FundRealVO;
import com.spring.cloud.fund.fundreal.service.IFundRealService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author haozai
 * @date 2021/5/28  15:13
 */
@Slf4j
@Api("基金实时控制器")
@RequestMapping("/fundReal")
@RestController
@AllArgsConstructor
public class FundRealController{

    private final IFundService iFundService;

    private final IFundRealService iFundRealService;

    @GetMapping("/addFundRealData")
    public void addFundRealData() {
        iFundService.searchFundRealData();
    }

    @GetMapping("/getFundRealData")
    public List<FundRealVO> getFundRealData() {
        return iFundRealService.getFundRealData();
    }

    @GetMapping("/getFundRealDataAnon")
    public List<FundRealVO> getFundRealDataAnon() {
        return iFundRealService.getFundRealData();
    }
}

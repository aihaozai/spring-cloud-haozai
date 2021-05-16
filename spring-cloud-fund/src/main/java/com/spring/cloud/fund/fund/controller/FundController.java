package com.spring.cloud.fund.fund.controller;


import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.service.IFundService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.core.result.Result;
import spring.cloud.base.fund.dto.FundDetailDataDto;
import spring.cloud.base.fund.util.FundDataUtil;

import javax.script.ScriptException;
import java.util.List;


/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Slf4j
@Api("基金公司控制器")
@RequestMapping("/fund")
@RestController
@AllArgsConstructor
public class FundController{

    private final IFundService iFundService;

    private final FundDataUtil fundDataUtil;

    @GetMapping("/getDetailDataChart")
    public Result<FundDetailDataDto> getDetailDataChart(@RequestParam String fundCode) throws ScriptException {
        return Result.ok(fundDataUtil.getFundDetailList(fundCode));
    }

    @GetMapping("/selectFund")
    public Result<List<Fund>> selectFund(){
        List<Fund> fundList = iFundService.list();
        return Result.ok(fundList);
    }
}

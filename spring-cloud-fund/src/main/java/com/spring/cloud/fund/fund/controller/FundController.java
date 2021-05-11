package com.spring.cloud.fund.fund.controller;

import com.alibaba.fastjson.JSON;
import com.spring.cloud.fund.core.dto.FundDetailDataDto;
import com.spring.cloud.fund.core.dto.FundRealDataDto;
import com.spring.cloud.fund.core.util.FundDataUtil;
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

    @GetMapping("/searchFundRealData")
    public Result searchFundRealData(){
        List<Fund> fundList = iFundService.list();
        long time = System.currentTimeMillis();
        fundList.parallelStream().forEach(f->{
            fundDataUtil.getFundListString(f.getFundCode());
           // log.info(f.getFundCode()+"=>{}", JSON.toJSON(fundDataUtil.getFundList(f.getFundCode())));
        });
        log.info("爬取基金实时数据结束，耗时："+(System.currentTimeMillis()-time)/1000);
        return Result.ok();
    }

}

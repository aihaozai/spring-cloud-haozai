package com.spring.cloud.fund.fund.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.service.IFundService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.core.result.Result;
import spring.cloud.base.datasource.util.QueryUtil;
import spring.cloud.base.fund.dto.FundDetailDataDTO;
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
    public Result<FundDetailDataDTO> getDetailDataChart(@RequestParam String fundCode) throws ScriptException {
        return Result.ok(fundDataUtil.getFundDetailDataDTO(fundCode));
    }

    @PostMapping("/selectFund")
    public Result<List<Fund>> selectFund(@RequestBody JSONObject jsonObject){
        List<Fund> fundList = iFundService.list(QueryUtil.setQuery(jsonObject, new QueryWrapper()));
        return Result.ok(fundList);
    }
}

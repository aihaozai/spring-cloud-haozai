package com.spring.cloud.fund.fund.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.service.IFundService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;
import spring.cloud.base.fund.dto.FundDetailDataDTO;
import spring.cloud.base.fund.util.FundDataUtil;
import javax.script.ScriptException;
import java.util.ArrayList;
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

    @Value("${search.fundCode}")
    private ArrayList<String> fundCodeList;

    private final IFundService iFundService;

    private final FundDataUtil fundDataUtil;

    @GetMapping("/getDetailDataChart")
    public FundDetailDataDTO getDetailDataChart(@RequestParam String fundCode) throws ScriptException {
        return fundDataUtil.getFundDetailDataDTO(fundCode);
    }

    @PostMapping("/selectFund")
    public List<Fund> selectFund(@RequestBody JSONObject jsonObject) {
        return iFundService.list(QueryUtil.setQuery(jsonObject, new QueryWrapper()));
    }

    @PostMapping("/page")
    public Page<Fund> page(@RequestBody QueryPage queryPage) {
        Page<Fund> page = iFundService.page(new Page<>(queryPage.getCurrent(), queryPage.getCurrent()),queryPage.getQueryWrapper());
        return page;
    }

    @GetMapping("/getSubscribeFund")
    public List<Fund> getFundRealData(){
        QueryWrapper<Fund> fundQueryWrapper = new QueryWrapper<>();
        fundQueryWrapper.in("fund_code",fundCodeList);
        List<Fund> fundList = iFundService.list(fundQueryWrapper);
        return fundList;
    }
}

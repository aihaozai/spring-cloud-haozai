package com.spring.cloud.fund.fund.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.query.FundQueryCriteria;
import com.spring.cloud.fund.fund.service.IFundService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;
import spring.cloud.base.fund.dto.FundDetailDataDTO;
import spring.cloud.base.fund.util.FundDataUtil;
import spring.cloud.base.resource.starter.util.OAuth2ResourceUtil;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Slf4j
@Api(tags = "基金控制器",value = "/fund")
@RequestMapping("/fund")
@RestController
@AllArgsConstructor
public class FundController{

    private final IFundService iFundService;

    private final FundDataUtil fundDataUtil;

    @GetMapping("/page")
    public Page<Fund> page(QueryPage queryPage, FundQueryCriteria queryCriteria) {
        return iFundService.page(new Page<>(queryPage.getCurrent(), queryPage.getSize()), QueryUtil.getPredicate(new QueryWrapper<Fund>(),queryCriteria));
    }

    @GetMapping("/getSubscribeFund")
    public List<Fund> getFundRealData(){
        QueryWrapper<Fund> fundQueryWrapper = new QueryWrapper<>();
        fundQueryWrapper.eq("s.user_id", OAuth2ResourceUtil.getUserId());
        return iFundService.findList(fundQueryWrapper);
    }

    @GetMapping("/getDetailDataChart")
    public FundDetailDataDTO getDetailDataChart(@RequestParam String fundCode) throws ScriptException {
        return fundDataUtil.getFundDetailDataDTO(fundCode);
    }
}

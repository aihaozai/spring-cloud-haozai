package com.spring.cloud.fund.funddetail.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.fund.funddetail.entity.FundDetail;
import com.spring.cloud.fund.funddetail.query.FundDetailQueryCriteria;
import com.spring.cloud.fund.funddetail.service.IFundDetailService;
import com.spring.cloud.fund.handler.SearchFundJobHandler;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;


/**
 * @author haozai
 * @date 2021/6/10  15:13
 */
@Slf4j
@Api("基金详细控制器")
@RequestMapping("/fundDetail")
@RestController
@AllArgsConstructor
public class FundDetailController {

    private final IFundDetailService iFundDetailService;

    private final SearchFundJobHandler searchFundJobHandler;

    @GetMapping("/page")
    public Page<FundDetail> page(QueryPage queryPage, FundDetailQueryCriteria queryCriteria) {
        return iFundDetailService.page(new Page<>(queryPage.getCurrent(), queryPage.getSize()), QueryUtil.getPredicate(new QueryWrapper<FundDetail>(),queryCriteria));
    }

    @PutMapping("/addFundDetailData")
    public void addFundDetailData() {
        searchFundJobHandler.searchFundDetailData();
    }
}

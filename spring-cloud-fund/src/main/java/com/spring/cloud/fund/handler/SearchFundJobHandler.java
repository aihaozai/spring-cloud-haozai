package com.spring.cloud.fund.handler;

import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.service.IFundService;
import com.spring.cloud.fund.fundReal.entity.FundReal;
import com.spring.cloud.fund.fundReal.service.IFundRealService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import spring.cloud.base.fund.service.IBaseSearchFundService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haozai
 * @description 基金爬取定时器
 * @date 2021/5/21  17:11
 */
@Slf4j
@AllArgsConstructor
@Component
public class SearchFundJobHandler {

    private final IFundService iFundService;

    private final IFundRealService iFundRealService;

    private final IBaseSearchFundService baseSearchFundService;

    @XxlJob("searchFundRealData")
    public void searchFundRealData() throws IOException {
        List<String> fundList = iFundService.list().stream().map(Fund::getFundCode).collect(Collectors.toList());
        List<FundReal> searchResult = baseSearchFundService.searchFundRealData(fundList, FundReal.class);
        long begin=System.currentTimeMillis();
        searchResult = searchResult.stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        iFundRealService.insertBatch(searchResult);
        long end=System.currentTimeMillis();
        log.info("基金数据更新完毕，耗时：{}",end-begin);
    }
}

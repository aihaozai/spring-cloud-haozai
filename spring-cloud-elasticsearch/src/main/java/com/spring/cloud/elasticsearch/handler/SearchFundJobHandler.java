package com.spring.cloud.elasticsearch.handler;

import com.spring.cloud.elasticsearch.searchFund.service.ISearchFundService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author haozai
 * @description 基金爬取定时器
 * @date 2021/5/21  17:11
 */
@AllArgsConstructor
@Component
public class SearchFundJobHandler {
    @Value("${search.fundCode}")
    private ArrayList<String> fundCodeList;

    private  ISearchFundService iSearchFundService;

    @XxlJob("fundRealDataJobHandler")
    public void fundRealDataJobHandler() throws IOException {
        iSearchFundService.searchFundRealData(fundCodeList);
    }

    @XxlJob("createFundIndexJobHandler")
    public void createFundIndexJobHandler(){
        iSearchFundService.createFundIndex(fundCodeList);
    }
}

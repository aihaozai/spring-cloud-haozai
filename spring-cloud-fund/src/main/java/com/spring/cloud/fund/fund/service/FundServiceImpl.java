package com.spring.cloud.fund.fund.service;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.mapper.FundMapper;
import com.spring.cloud.fund.fundreal.entity.FundReal;
import com.spring.cloud.fund.fundreal.service.IFundRealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import spring.cloud.base.fund.service.IBaseSearchFundService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements IFundService {

    private final static int BATCH_SIZE = 2000;
    private final IBaseSearchFundService baseSearchFundService;
    private final IFundRealService iFundRealService;
    private final FundMapper fundMapper;

    @Override
    public List<Fund> findList(QueryWrapper queryWrapper) {
        return fundMapper.findList(queryWrapper);
    }

    @Override
    public void searchFundRealData() {
        long begin = System.currentTimeMillis();
        List<String> fundList = new ArrayList<>();

        ExecutorService threadPool = new ThreadPoolExecutor(200,200,10L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(),new ThreadFactoryBuilder().setNamePrefix("thread-call-runner-%d").build());

        this.fundMapper.fundCodeList(resultContext -> {
            fundList.add(resultContext.getResultObject());
            if (fundList.size() == BATCH_SIZE) {
                batchHandle(fundList,threadPool);
            }
        });
        if (fundList.size()>0) {
            batchHandle(fundList,threadPool);
        }
        threadPool.shutdown();
        while(true){
            if(threadPool.isTerminated()){
                log.info("爬取基金实时数据结束，耗时："+(System.currentTimeMillis()-begin)/1000);
                break;
            }
        }
    }

    /**
     * 批量数据处理
     */
    private void batchHandle(List<String> fundList,ExecutorService threadPool) {
        try {
            List<FundReal> searchResult = baseSearchFundService.searchFundRealData(fundList, FundReal.class,threadPool);
            searchResult = searchResult.stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            this.iFundRealService.insertBatch(searchResult);

            searchResult.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fundList.clear();
        }
    }
}

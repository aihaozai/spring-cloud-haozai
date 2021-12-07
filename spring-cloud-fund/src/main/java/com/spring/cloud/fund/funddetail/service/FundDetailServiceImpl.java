package com.spring.cloud.fund.funddetail.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.funddetail.entity.FundDetail;
import com.spring.cloud.fund.funddetail.mapper.FundDetailMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import spring.cloud.base.fund.dto.FundDetailDataDTO;
import spring.cloud.base.fund.util.FundDataUtil;
import spring.cloud.base.resource.starter.util.OAuth2ResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haozai
 * @date 2021/6/10  15:13
 */
@Slf4j
@Service
@AllArgsConstructor
public class FundDetailServiceImpl extends ServiceImpl<FundDetailMapper, FundDetail> implements IFundDetailService {

    private final FundDetailMapper fundDetailMapper;

    private final FundDataUtil fundDataUtil;

    @Override
    public List<FundDetail> searchFundDetailData(List<Fund> fundList) {
        long time = System.currentTimeMillis();
        ExecutorService threadPool = new ThreadPoolExecutor(160,160,200L,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(1100),new ThreadFactoryBuilder().setNamePrefix("thread-call-runner-%d").build());

        AtomicInteger num = new AtomicInteger();
        List<FundDetail> result =new ArrayList<>();
        this.executeSearch(threadPool,fundList,num,result);
        threadPool.shutdown();
        while(true){
            if(threadPool.isTerminated()){
                log.info("失败数量："+num.get()+"爬取基金详细数据结束，耗时："+(System.currentTimeMillis()-time)/1000);
                break;
            }
        }
        return result;
    }

    @Override
    public void insertBatch(List<FundDetail> fundDetailList) {
        this.fundDetailMapper.insertBatch(fundDetailList);
    }

    @Override
    public Page<FundDetail> subscribePage(Page page, QueryWrapper predicate) {
        return this.fundDetailMapper.subscribePage(page, OAuth2ResourceUtil.getUserId(),predicate);
    }


    private void executeSearch(ExecutorService threadPool, List<Fund> fundList, AtomicInteger num, List<FundDetail> result){
        List<Fund> lastList = new ArrayList<>();
        num.getAndSet(fundList.size());
        fundList.forEach(l->
                threadPool.execute(() -> {

                            try {
                                FundDetailDataDTO data = fundDataUtil.getFundDetailDataDTOMin(l.getFundCode());
                                if(ObjectUtils.isNotEmpty(data)){
                                    FundDetail fundDetail = new FundDetail();
                                    fundDetail.setFundCode(l.getFundCode());
                                    fundDetail.setFundName(l.getFundName());
                                    fundDetail.setCompanyCode(l.getCompanyCode());
                                    fundDetail.setCompanyName(l.getCompanyName());
                                    fundDetail.setFundType(l.getFundType());
                                    fundDetail.setOneN(data.getSyl_1n());
                                    fundDetail.setSixY(data.getSyl_6y());
                                    fundDetail.setThreeY(data.getSyl_3y());
                                    fundDetail.setOneY(data.getSyl_1y());
                                    result.add(fundDetail);
                                }
                                log.debug(l + "=>{}", data);
                            } catch (Exception e) {
                                lastList.add(l);
                            }
                            num.getAndDecrement();
                        }
                ));
        while(true){
            if(num.get()==0){
                break;
            }
        }
        if(CollectionUtil.isNotEmpty(lastList)){
            this.executeSearch(threadPool,lastList,num,result);
        }
    }
}

package com.spring.cloud.fund.fund.service;

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
        this.fundMapper.fundCodeList(resultContext -> {
            fundList.add(resultContext.getResultObject());
            if (fundList.size() == BATCH_SIZE) {
                batchHandle(fundList);
            }
        });
        if (fundList.size()>0) {
            batchHandle(fundList);
        }
        long end = System.currentTimeMillis();
        log.info("基金实时数据更新完毕，耗时：{}", end - begin);
    }

    /**
     * 批量数据处理
     */
    private void batchHandle(List<String> fundList) {
        try {
            List<FundReal> searchResult = baseSearchFundService.searchFundRealData(fundList, FundReal.class);
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

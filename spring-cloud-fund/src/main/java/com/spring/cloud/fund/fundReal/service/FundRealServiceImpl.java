package com.spring.cloud.fund.fundReal.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fundReal.entity.FundReal;
import com.spring.cloud.fund.fundReal.mapper.FundRealMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haozai
 *@date 2021/5/28  15:13
 */

@Service
@AllArgsConstructor
public class FundRealServiceImpl extends ServiceImpl<FundRealMapper, FundReal> implements IFundRealService {

    private final FundRealMapper fundRealMapper;

    @Override
    public void insertBatch(List<FundReal> fundRealList) {
        this.fundRealMapper.insertBatch(fundRealList);
    }
}

package com.spring.cloud.fund.fundReal.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fundReal.entity.FundReal;
import com.spring.cloud.fund.fundReal.mapper.FundRealMapper;
import org.springframework.stereotype.Service;

/**
 * @author haozai
 *@date 2021/5/28  15:13
 */
@Service
public class FundRealServiceImpl extends ServiceImpl<FundRealMapper, FundReal> implements IFundRealService {

}

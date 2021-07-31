package com.spring.cloud.fund.fundsubscribe.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fundsubscribe.entity.FundSubscribe;
import com.spring.cloud.fund.fundsubscribe.mapper.FundSubscribeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.cloud.base.core.util.AuthUtil;

/**
 * @author haozai
 *@date 2021/7/31  15:13
 */
@Slf4j
@Service
@AllArgsConstructor
public class FundSubscribeServiceImpl extends ServiceImpl<FundSubscribeMapper, FundSubscribe> implements IFundSubscribeService {

    @Override
    public void subscribe(String fundCode) {
        super.save(FundSubscribe.builder().userId(AuthUtil.getUserId()).fundCode(fundCode).build());
    }
}

package com.spring.cloud.fund.fundsubscribe.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fundsubscribe.entity.FundSubscribe;
import com.spring.cloud.fund.fundsubscribe.mapper.FundSubscribeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import spring.cloud.base.core.util.AuthUtil;
import spring.cloud.base.resource.starter.util.OAuth2ResourceUtil;

import java.util.List;
import java.util.stream.Collectors;

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
        super.save(FundSubscribe.builder().userId(OAuth2ResourceUtil.getUserId()).fundCode(fundCode).build());
    }

    @Override
    public List<String> subscribeCode() {

        LambdaQueryWrapper<FundSubscribe> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FundSubscribe::getUserId, OAuth2ResourceUtil.getUserId());
        List<FundSubscribe> list = super.list(queryWrapper);
        if(CollectionUtil.isNotEmpty(list)){
            return list.stream().map(FundSubscribe::getFundCode).collect(Collectors.toList());
        }
        return null;
    }
}

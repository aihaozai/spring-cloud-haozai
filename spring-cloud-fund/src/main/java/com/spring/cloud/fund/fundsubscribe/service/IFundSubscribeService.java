package com.spring.cloud.fund.fundsubscribe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.fund.fundsubscribe.entity.FundSubscribe;

import java.util.List;

/**
 * @author haozai
 * @date 2021/7/31  15:13
 */
public interface IFundSubscribeService  extends IService<FundSubscribe> {

    /**
     * 订阅基金
     *
     * @param fundCode 基金代码
     */
    void subscribe(String fundCode);

    /**
     * 当前用户获取所有订阅基金
     *
     * @return java.util.List<java.lang.String>
     */
    List<String> subscribeCode();
}

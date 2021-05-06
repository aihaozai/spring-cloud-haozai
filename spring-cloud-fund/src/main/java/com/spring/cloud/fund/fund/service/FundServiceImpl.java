package com.spring.cloud.fund.fund.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.mapper.FundMapper;
import org.springframework.stereotype.Service;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Service
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements IFundService {

}

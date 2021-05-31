package com.spring.cloud.fund.fundReal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.service.IFundService;
import com.spring.cloud.fund.fundReal.entity.FundReal;
import com.spring.cloud.fund.fundReal.mapper.FundRealMapper;
import com.spring.cloud.fund.fundReal.service.IFundRealService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.core.result.Result;
import spring.cloud.base.fund.service.IBaseSearchFundService;

import java.io.IOException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author haozai
 * @date 2021/5/28  15:13
 */
@Api("基金实时控制器")
@RequestMapping("/fundReal")
@RestController
@AllArgsConstructor
public class FundRealController{

    @Value("${search.fundCode}")
    private ArrayList<String> fundCodeList;

    private final IFundService iFundService;

    private final IFundRealService iFundRealService;

    private final IBaseSearchFundService baseSearchFundService;

    private final SqlSessionFactory sqlSessionFactory;

    @GetMapping("/addFundRealData")
    public Result addFundRealData() throws Exception {

        List<String> fundList = iFundService.list().stream().map(Fund::getFundCode).collect(Collectors.toList());
        List<FundReal> searchResult = baseSearchFundService.searchFundRealData(fundList, FundReal.class);
      //  SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
       //FundRealMapper mapper = sqlSession.getMapper(FundRealMapper.class);
        long begin=System.currentTimeMillis();
        searchResult = searchResult.stream().filter(f->ObjectUtils.isNotEmpty(f)).collect(Collectors.toList());
        iFundRealService.insertBatch(searchResult);
        long end=System.currentTimeMillis();
        System.out.println(end-begin);
        //sqlSession.commit();
        //sqlSession.close();
        return Result.ok();
    }

    @GetMapping("/getFundRealData")
    public Result getFundRealData(){
        QueryWrapper<Fund> fundQueryWrapper = new QueryWrapper<>();
        QueryWrapper<FundReal> fundRealQueryWrapper = new QueryWrapper<>();
        fundQueryWrapper.in("fund_code",fundCodeList);
        fundRealQueryWrapper.in("fundcode",fundCodeList);
        List<Fund> fundList = iFundService.list(fundQueryWrapper);
        List<FundReal> fundRealList = iFundRealService.list(fundRealQueryWrapper);
        fundList.forEach(f->
            f.setFundRealList(fundRealList.stream()
                .filter(c->f.getFundCode().equals(c.getFundcode()))
                    .sorted(Comparator.comparing(FundReal::getGztime)).collect(Collectors.toList()))
        );
        return Result.ok(fundList);
    }
}

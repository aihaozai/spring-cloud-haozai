package com.spring.cloud.fund.fundreal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.fund.fundreal.entity.FundReal;
import com.spring.cloud.fund.fundreal.mapper.FundRealMapper;
import com.spring.cloud.fund.fundreal.model.FundRealVO;
import com.spring.cloud.fund.fundsubscribe.service.IFundSubscribeService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author haozai
 *@date 2021/5/28  15:13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FundRealServiceImpl extends ServiceImpl<FundRealMapper, FundReal> implements IFundRealService {

    private final FundRealMapper fundRealMapper;

    private final IFundSubscribeService subscribeService;

    @Override
    public void insertBatch(List<FundReal> fundRealList) {
        this.fundRealMapper.insertBatch(fundRealList);
    }

    @Override
    public String queryLastDate() {
        return  this.fundRealMapper.queryLastDate();
    }

    @Override
    public List<FundRealVO> getFundRealData() {
        return this.getFundReal(subscribeService.subscribeCode());
    }

    @Override
    public void deleteByDate(String delDate) {
        log.info("删除索引");
        this.fundRealMapper.delIndex();
        log.info("删除某日期之前基金实时信息");
        this.fundRealMapper.deleteByDate(delDate);
        log.info("添加索引");
        this.fundRealMapper.addIndex();
    }

    private List<FundRealVO> getFundReal(List<String> codeList){
        if (CollectionUtils.isEmpty(codeList)) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        String date = this.queryLastDate();

        QueryWrapper<FundReal> fundRealQueryWrapper = new QueryWrapper<>();
        fundRealQueryWrapper.eq("searchtime",date);
        fundRealQueryWrapper.in("fundcode",codeList);

        List<FundRealVO> voList = fundRealMapper.fundList(new QueryWrapper<>().in("fund_code",codeList));
        List<FundReal> fundRealList = super.list(fundRealQueryWrapper);
        voList.forEach(f-> {
                    List<FundReal> filterList = fundRealList.stream()
                            .filter(c -> f.getFundCode().equals(c.getFundcode()))
                            .sorted(Comparator.comparing(FundReal::getGztime)).collect(Collectors.toList());
                    f.setFundRealList(filterList);

                    double max = filterList.stream().mapToDouble(t->t.getGszzl().doubleValue()).max().orElse(0);
                    double min = filterList.stream().mapToDouble(t->t.getGszzl().doubleValue()).min().orElse(0);
                    //计算Y轴数值
                    List<String> yAxis  = new ArrayList<>();
                    if(Double.doubleToLongBits(max)!=Double.doubleToLongBits(min)){
                        double num =  Double.valueOf(df.format((max-min)/3));
                        yAxis.add(df.format(min-num));
                        yAxis.add(df.format(min));
                        IntStream.range(1,3).forEach(i-> yAxis.add(df.format(min+i*num)));
                        yAxis.add(df.format(max));
                        yAxis.add(df.format(max+num));
                    }else{
                        double num =  Double.valueOf(df.format(max/4));
                        yAxis.add(df.format(max+num));
                        yAxis.add(df.format(max));
                        IntStream.range(1,4).forEach(i-> yAxis.add(df.format(max-i*num)));
                    }
                    f.setYAxis(yAxis);
                }
        );
        return voList;
    }
}

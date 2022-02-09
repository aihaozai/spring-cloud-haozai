package spring.cloud.base.fund.service;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import spring.cloud.base.fund.util.FundDataUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haozai
 * @description 搜索基金服务
 * @date 2021/5/27  21:52
 */
@Slf4j
@Service
@AllArgsConstructor
public class BaseSearchFundServiceImpl implements IBaseSearchFundService{

    private final FundDataUtil fundDataUtil;

    @Override
    public <T> List<T> searchFundRealData(List<String> fundList, Class<T> clazz,ExecutorService threadPool){
        AtomicInteger num = new AtomicInteger();
        List<T> result =new ArrayList<>();
        this.executeSearch(threadPool,fundList,num,result,clazz);
        return result;
    }

    private <T> void executeSearch(ExecutorService threadPool, List<String> fundList,
                                   AtomicInteger num, List<T> result, Class<T> clazz){
        List<String> lastList = new ArrayList<>();
        num.getAndSet(fundList.size());
        fundList.forEach(l->
            threadPool.execute(() -> {
                try {
                    T data = fundDataUtil.getFundRealData(l,clazz);
                    if(ObjectUtils.isNotEmpty(data)){
                        result.add(data);
                    }
                    log.debug(l + "=>{}", data);
                    } catch (Exception e) {
                    lastList.add(l);
                    }
                    num.getAndDecrement();
                }
            )
        );
        while(true){
            if(num.get()==0){
                break;
            }
        }
        if(CollectionUtil.isNotEmpty(lastList)){
            this.executeSearch(threadPool,lastList,num,result,clazz);
        }
    }
}

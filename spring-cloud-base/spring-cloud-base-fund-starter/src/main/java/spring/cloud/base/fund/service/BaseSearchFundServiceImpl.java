package spring.cloud.base.fund.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import spring.cloud.base.fund.util.FundDataUtil;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
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
    public <T> List<T>  searchFundRealData(List<String> fundList, Class<T> clazz){
        long time = System.currentTimeMillis();
        ExecutorService threadPool = new ThreadPoolExecutor(100,200,200L,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(200),new ThreadFactoryBuilder().setNamePrefix("thread-call-runner-%d").build());

        AtomicInteger num = new AtomicInteger();
        List<T> result =new ArrayList<>();
        this.executeSearch(threadPool,fundList,num,result,clazz);
        threadPool.shutdown();
        while(true){
            if(threadPool.isTerminated()){
                log.info("失败数量："+num.get()+"爬取基金实时数据结束，耗时："+(System.currentTimeMillis()-time)/1000);
                break;
            }
        }
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

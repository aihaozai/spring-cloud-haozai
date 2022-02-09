package spring.cloud.base.fund.service;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author haozai
 * @date 2021/5/27  22:03
 */
public interface IBaseSearchFundService {

    /**
     * 爬取实时基金
     *
     * @param fundList
     * @param clazz
     * @param threadPool
     * @return java.util.List<T>
     */
    <T> List<T> searchFundRealData(List<String> fundList, Class<T> clazz, ExecutorService threadPool);
}

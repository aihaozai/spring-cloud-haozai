package spring.cloud.base.fund.service;

import spring.cloud.base.fund.dto.FundDto;

import java.io.IOException;
import java.util.List;

/**
 * @author haozai
 * @date 2021/5/27  22:03
 */
public interface IBaseSearchFundService {
    /**
     * 爬取实时基金
     * @param fundList
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> List<T> searchFundRealData(List<String> fundList,Class<T> clazz) throws IOException;
}

package com.spring.cloud.elasticsearch.searchFund.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.spring.cloud.elasticsearch.core.service.ElasticSearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import spring.cloud.base.fund.dto.FundRealDataDTO;
import spring.cloud.base.fund.service.IBaseSearchFundService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import static cn.hutool.core.date.DatePattern.NORM_DATE_PATTERN;
import static com.spring.cloud.elasticsearch.constant.ElasticSearchConstant.INDEX;
import static com.spring.cloud.elasticsearch.constant.ElasticSearchConstant.SPLIT;

/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Slf4j
@Service
@AllArgsConstructor
public class SearchFundServiceImpl implements ISearchFundService {

    private final ElasticSearchService elasticSearchService;

    private final RestHighLevelClient client;

    @Override
    public void createFundIndex(List<String> fundCodeList) {
        String date = DateUtil.format(new Date(),NORM_DATE_PATTERN);
        fundCodeList.parallelStream().forEach(f->{
            try {
                elasticSearchService.createIndex(f + INDEX + date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void getFundRealData(List<String> fundList) throws IOException {
        String date = DateUtil.format(new Date(),NORM_DATE_PATTERN);
        String codes = fundList.stream().collect(Collectors.joining(String.format("%s%s%s", INDEX, date, SPLIT)))+INDEX + date;

        // ??????????????????
        SearchRequest searchRequest = new SearchRequest().indices(codes.split(SPLIT));

        // ?????????????????????
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.from(0).size(9000);

        //????????????
        String[] excludes = new String[]{"dwjz","jzrq","dwjz"};

        searchSourceBuilder.fetchSource(null,  excludes);

        searchRequest.source(searchSourceBuilder);

        // ????????????,???ES??????http??????
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // ????????????
        SearchHits hits = searchResponse.getHits();

        // ????????????????????????
        TotalHits totalHits = hits.getTotalHits();

        // ???????????????????????????
        SearchHit[] searchHits = hits.getHits();
        System.out.println(totalHits);
    }

    private void insertFundData(List<FundRealDataDTO> data) throws IOException {
        String date = DateUtil.format(new Date(),NORM_DATE_PATTERN);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        data.forEach(d-> bulkRequest.add(
                new IndexRequest(d.getFundcode()+ INDEX + date)
                        .id(d.getGztime())
                        .source(JSON.toJSONString(d), XContentType.JSON)
        ));
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("?????????{},?????????{}",bulkResponse.hasFailures(),bulkResponse.buildFailureMessage());
    }
}

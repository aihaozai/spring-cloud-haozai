package com.spring.cloud.elasticsearch.core.service;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * @author haozai
 * @description 服务实现类
 * @date 2021/4/20  22:29
 */
@Slf4j
@Service
@AllArgsConstructor
public class ElasticSearchService {
    private final RestHighLevelClient client;

    public void createIndex(String indexKey)throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexKey);
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        log.debug("创建索引响应:{}",new JSONObject(createIndexResponse));
    }
}

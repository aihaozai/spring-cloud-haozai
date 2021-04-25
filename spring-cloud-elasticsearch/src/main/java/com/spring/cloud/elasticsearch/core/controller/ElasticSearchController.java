package com.spring.cloud.elasticsearch.core.controller;

import com.spring.cloud.elasticsearch.core.service.ElasticSearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

/**
 * @author haozai
 * @description spring-cloud-haozai
 * @date 2021/4/20  22:46
 */

@Slf4j
@RequestMapping("/elasticSearch")
@RestController
@AllArgsConstructor
public class ElasticSearchController {

    private final ElasticSearchService elasticSearchService;

    @PutMapping("/createIndex")
    public void createIndex(@RequestParam String indexKey)throws IOException {
        elasticSearchService.createIndex(indexKey);
    }
}

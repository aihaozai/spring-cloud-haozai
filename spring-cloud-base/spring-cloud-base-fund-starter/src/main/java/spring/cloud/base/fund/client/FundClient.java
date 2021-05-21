package spring.cloud.base.fund.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spring.cloud.base.fund.dto.FundDto;
import spring.cloud.base.fund.result.Result;

import java.util.List;

/**
 * @author haozai
 * @date 2021/5/16  14:04
 */
@Component
@FeignClient(value = "cloud-fund", path = "fund/fund")
public interface FundClient {
    /**
     * 查询基金
     * @param jsonObject
     * @return
     */
    @PostMapping("/selectFund")
    Result<List<FundDto>> selectFund(@RequestBody JSONObject jsonObject);
}

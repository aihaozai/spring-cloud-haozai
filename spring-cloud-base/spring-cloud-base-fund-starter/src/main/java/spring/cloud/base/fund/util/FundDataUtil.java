package spring.cloud.base.fund.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.cloud.base.fund.dto.FundDetailDataDto;
import spring.cloud.base.fund.dto.FundRealDataDto;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author haozai
 * @date 2021/5/7 13:59
 */
@Slf4j
@Component
@AllArgsConstructor
public class FundDataUtil {

    /**
     * 基金
     */
    private final static String FUND_URL = "http://fundgz.1234567.com.cn/js/%s.js";
    /**
     * 基金详细
     */
    private final static String FUND_DETAIL_URL = "http://fund.eastmoney.com/pingzhongdata/%s.js";

    private final static String  PATTERN = "jsonpgz\\(|\\);";

    private final RestTemplate restTemplate;

    public FundRealDataDto getFundList(String fundCode){
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(FUND_URL,fundCode), String.class );
        if(StringUtils.isEmpty(response.getBody())){
            return null;
        }
        String data = response.getBody().replaceAll(PATTERN,"");
        return JSONObject.parseObject(data, FundRealDataDto.class);
    }

    public  String getFundListString(String fundCode){
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(FUND_URL,fundCode), String.class );
        if(StringUtils.isEmpty(response.getBody())){
            return null;
        }
        return response.getBody().replaceAll(PATTERN,"");
    }

    public FundDetailDataDto getFundDetailList(String fundCode) throws ScriptException {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(FUND_DETAIL_URL,fundCode), String.class );
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engineByName = engineManager.getEngineByName("JavaScript");
        engineByName.eval(response.getBody());
        ScriptObjectMirror o = (ScriptObjectMirror)engineByName.get("Data_netWorthTrend");
        ScriptObjectMirror o1 = (ScriptObjectMirror)engineByName.get("Data_ACWorthTrend");
        FundDetailDataDto fundDetailDataDto = new FundDetailDataDto();
        fundDetailDataDto.setFS_name(engineByName.get("fS_name").toString())
                .setFS_code(engineByName.get("fS_code").toString())
                .setFund_sourceRate(engineByName.get("fund_sourceRate").toString())
                .setFund_Rate(engineByName.get("fund_Rate").toString())
                .setFund_minsg(engineByName.get("fund_minsg").toString())
                .setZqCodesNew(engineByName.get("zqCodesNew").toString())
                .setSyl_1n(engineByName.get("syl_1n").toString())
                .setSyl_6y(engineByName.get("syl_6y").toString())
                .setSyl_3y(engineByName.get("syl_3y").toString())
                .setSyl_1y(engineByName.get("syl_1y").toString())
        .setData_netWorthTrend(JSONArray.parseArray(JSONArray.toJSON(o.values()).toString()))
        .setData_ACWorthTrend(JSONArray.parseArray(JSONArray.toJSON(o1.values()).toString()));
        return fundDetailDataDto;

    }

//    public static void main(String[] args) throws ScriptException {
//        String[] arr = new String[]{"519674","005693","320007","008087","001717"};
//        for (String code :arr){
//            System.out.println(getFundDetailList(code).toString());
//        }
//    }
}

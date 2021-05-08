package com.spring.cloud.fund.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.spring.cloud.fund.core.dto.FundDetailDataDto;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @ClassName ScriptEngineTest
 * @Description ToDo
 * @Author qinfuhong
 * @Date 2019/4/18 19:30
 * @Version 1.0
 **/
public class ScriptEngineTest {
    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engineByName = engineManager.getEngineByName("JavaScript");
        String jsFileName = "C:\\Users\\Administrator\\Desktop\\519983.js";

        File file = new File(jsFileName);


        //读取js文件
        FileReader reader = new FileReader(file);
        //执行指定脚本
        engineByName.eval(reader);
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
                .setSyl_1y(engineByName.get("syl_1y").toString());
                //.setData_netWorthTrend(JSON.parseArray(engineByName.get("Data_netWorthTrend").toString()))
                //.setData_ACWorthTrend(JSON.parseArray(engineByName.get("Data_ACWorthTrend").toString()));

        ScriptObjectMirror o = (ScriptObjectMirror)engineByName.get("Data_netWorthTrend");
        System.out.println(engineByName.get("Data_netWorthTrend").toString());
        System.out.println(JSONArray.toJSON(o.values()));
        System.out.println(fundDetailDataDto.toString());
        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSON(o.values()).toString());

        reader.close();

    }
}


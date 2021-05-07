package com.spring.cloud.fund.core.util;

import com.spring.cloud.fund.fund.entity.Fund;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haozai
 * @date 2021/5/7 10:27
 */
@Slf4j
public class FundSearchUtil {

    public final static String URL = "http://fund.eastmoney.com/Company/%s.html";

    /**
     * 爬取某基金公司下的基金
     * @param companyCode
     * @return
     * @throws IOException
     */
    public static List<Fund>  getFundList(String companyCode)throws IOException {
        log.info("获取基金开始----公司编号："+companyCode);
        Document doc = DocumentUtil.getDocument(String.format(URL,companyCode));
        Elements html = doc.getElementById("kfsFundNetWrap").select("tbody").select("tr");
        List<Fund> fundList = new ArrayList<>();
        String name  =doc.getElementsByClass("ttjj-panel-main-title").html();
        for(Element element : html){
            if(element.html().contains("暂无数据")){
                break;
            }
            Fund fund = new Fund();
            fund.setFundCode(element.select("[class=code]").html())
                .setFundName(element.select("[class=name]").html())
                .setFundType(element.children().get(2).html())
                .setCompanyCode(companyCode)
                .setCompanyName(name);
            fundList.add(fund);
        }
        log.info("获取基金完毕----基金公司："+name+"，公司编号："+companyCode+"，基金数量："+fundList.size());

        return fundList;
    }
}

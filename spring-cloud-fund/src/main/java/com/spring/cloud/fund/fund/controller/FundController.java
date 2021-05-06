package com.spring.cloud.fund.fund.controller;

import cn.hutool.core.lang.UUID;
import com.spring.cloud.fund.fund.entity.Fund;
import com.spring.cloud.fund.fund.service.IFundService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author haozai
 * @date 2021/5/06  15:13
 */
@Api("基金公司控制器")
@RequestMapping("/fund")
@RestController
@AllArgsConstructor
public class FundController{

    private final IFundService iFundService;

    @GetMapping("/te")
    public void te() throws IOException {

        String fileName = "C:\\Users\\Administrator\\Desktop\\fundcode_search.js";
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        while (line != null) {

            line = line.trim();
            String[] f = line.split("],");
            for (String n :f){
                n   = n.replace("]","").replace("[","").replace("\"","");
                String[] arr = n.split(",");
                System.out.println(UUID.randomUUID()+arr[0]+arr[1]);
                Fund fund = new Fund();
                fund.setFundCode(arr[0]).setFundName(arr[2]).setFundType(arr[3]);
                this.iFundService.save(fund);
            }
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        fileReader.close();
    }

}

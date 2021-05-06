package com.spring.cloud.fund.fundCompany.controller;

import cn.hutool.core.lang.UUID;
import com.spring.cloud.fund.fundCompany.entity.FundCompany;
import com.spring.cloud.fund.fundCompany.service.IFundCompanyService;
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
@RequestMapping("/fundCompany")
@RestController
@AllArgsConstructor
public class FundCompanyController{

    private final IFundCompanyService iFundCompanyService;

    @GetMapping("/te")
    public void te() throws IOException {

        String fileName = "C:\\Users\\Administrator\\Desktop\\jjjz_gs.js";
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
                FundCompany fundCompany = new FundCompany();
                fundCompany.setCompanyCode(arr[0]).setCompanyName(arr[1]);
                this.iFundCompanyService.save(fundCompany);
            }
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        fileReader.close();
    }

    public static void main(String[] args) {
        System.out.println(UUID.fastUUID().toString().replaceAll("-",""));
        System.out.println("dc379b86a4a133a1a3d0613711df0aa0".length());
    }
}

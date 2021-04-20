package com.spring.cloud.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.entity.User;
import com.spring.cloud.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springCloud
 * @author: haozai
 * @date: 2020-04-12 14:11
 **/
@Api("testController")
@RefreshScope
@RequestMapping("/res")
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @Value("${me}")
    private String me;
    @ApiOperation(value = "me", httpMethod = "GET")
    @GetMapping("/me")
    public String me(){
        return this.me;
    }

    @GetMapping("/page")
    public Object selectPage(){
        IPage<User> page = userService.selectUserPage(new Page<>(1, 1));
        return page;
    }
}

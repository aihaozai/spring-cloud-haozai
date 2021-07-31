package com.spring.cloud.auth.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.user.entity.User;
import com.spring.cloud.auth.user.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.datasource.request.QueryPage;

/**
 * @author haozai
 * @date 2021/4/30  11:19
 */
@Api("用户控制器")
@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController{

    private final IUserService iUserService;

    @GetMapping("/page")
    public Page<User> selectPage(QueryPage queryPage){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = iUserService.page(new Page<>(queryPage.getCurrent(), queryPage.getCurrent()),queryWrapper);
        return page;
    }
}

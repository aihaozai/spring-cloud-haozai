package com.spring.cloud.auth.system.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.system.user.model.UserQueryCriteria;
import com.spring.cloud.auth.system.user.model.UserVO;
import com.spring.cloud.auth.system.user.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;

/**
 * @author haozai
 * @date 2021/4/30  11:19
 */
@Api(value = "用户接口")
@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController{

    private final IUserService userService;

    @GetMapping("/page")
    public Page<UserVO> page(QueryPage queryPage, UserQueryCriteria queryCriteria) {
        return userService.selectUserPage(new Page<>(queryPage.getCurrent(), queryPage.getSize()), QueryUtil.getPredicate(new QueryWrapper<UserVO>(),queryCriteria));
    }
}

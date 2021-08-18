package com.spring.cloud.auth.menu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.model.MenuQueryCriteria;
import com.spring.cloud.auth.menu.service.IMenuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
@Api(value = "菜单接口")
@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class MenuController {

    private final IMenuService iMenuService;

    @GetMapping("/page")
    public Page<Menu> page(QueryPage queryPage, MenuQueryCriteria queryCriteria) {
        return iMenuService.page(new Page<>(queryPage.getCurrent(), queryPage.getSize()), QueryUtil.getPredicate(new QueryWrapper<Menu>(),queryCriteria));
    }
}

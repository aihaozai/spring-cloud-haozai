package com.spring.cloud.auth.menu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.model.MenuDTO;
import com.spring.cloud.auth.menu.model.MenuQueryCriteria;
import com.spring.cloud.auth.menu.model.MenuTree;
import com.spring.cloud.auth.menu.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;

import java.util.List;
import java.util.Map;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
@Api(value = "菜单接口")
@RequestMapping("/menu")
@RestController
@AllArgsConstructor
public class MenuController {

    private final IMenuService iMenuService;

    @GetMapping("/treePage")
    public Page treePage(QueryPage queryPage, MenuQueryCriteria queryCriteria) {
        queryCriteria.setPid(0L);
        return iMenuService.treePage(new Page<>(queryPage.getCurrent(), queryPage.getSize()), QueryUtil.getPredicate(new QueryWrapper<Menu>(),queryCriteria));
    }

    @ApiOperation("新增菜单")
    @PostMapping("/add")
    public void add(@RequestBody @Validated MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO,menu);
        System.out.println(menu.toString());
        iMenuService.save(menu);
    }

    @ApiOperation("下拉选择")
    @GetMapping("/select")
    public List<Map> select() {
        return iMenuService.select();
    }
}

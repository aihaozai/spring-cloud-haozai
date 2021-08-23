package com.spring.cloud.auth.menu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.model.MenuDTO;
import com.spring.cloud.auth.menu.model.MenuQueryCriteria;
import com.spring.cloud.auth.menu.model.MenuTree;
import com.spring.cloud.auth.menu.service.IMenuService;
import com.spring.cloud.auth.util.MenuTreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.core.dto.SelectDTO;
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
        queryCriteria.setPid(MenuTreeUtil.PID);
        return iMenuService.treePage(new Page<>(queryPage.getCurrent(), queryPage.getSize()), QueryUtil.getPredicate(new QueryWrapper<Menu>(),queryCriteria));
    }

    @ApiOperation("新增菜单")
    @PostMapping("/add")
    public void add(@RequestBody @Validated MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO,menu);
        iMenuService.save(menu);
    }

    @ApiOperation("编辑菜单")
    @PutMapping("/edit")
    public void edit(@RequestBody @Validated MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO,menu);
        iMenuService.updateById(menu);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/delete/{id}")
    public void edit(@PathVariable String id) {
        iMenuService.removeById(id);
    }

    @ApiOperation("下拉选择")
    @GetMapping("/select")
    public List<SelectDTO> select() {
        return iMenuService.select();
    }
}

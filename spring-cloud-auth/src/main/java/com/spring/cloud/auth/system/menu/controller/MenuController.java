package com.spring.cloud.auth.system.menu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.system.menu.entity.Menu;
import com.spring.cloud.auth.system.menu.model.MenuDTO;
import com.spring.cloud.auth.system.menu.model.MenuQueryCriteria;
import com.spring.cloud.auth.system.menu.model.MenuTree;
import com.spring.cloud.auth.system.menu.service.IMenuService;
import com.spring.cloud.auth.util.MenuTreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.core.dto.SelectDTO;
import spring.cloud.base.core.util.AuthUtil;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;

import java.util.List;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
@Api(value = "菜单接口")
@RequestMapping("/menu")
@RestController
@AllArgsConstructor
public class MenuController {

    private final IMenuService menuService;

    @GetMapping("/treePage")
    public Page treePage(QueryPage queryPage, MenuQueryCriteria queryCriteria) {
        queryCriteria.setPid(MenuTreeUtil.PID);
        return menuService.treePage(new Page<>(queryPage.getCurrent(), queryPage.getSize()), QueryUtil.getPredicate(new QueryWrapper<Menu>(),queryCriteria));
    }

    @PreAuthorize("hasAuthority('menu:add')")
    @ApiOperation("新增菜单")
    @PostMapping("/add")
    public void add(@RequestBody @Validated MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO,menu);
        menuService.save(menu);
    }

    @PreAuthorize("hasAuthority('menu:edit') or hasAnyRole()")
    @ApiOperation("编辑菜单")
    @PutMapping("/edit")
    public void edit(@RequestBody @Validated MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO,menu);
        menuService.updateById(menu);
    }

    @PreAuthorize("hasAuthority('menu:delete')")
    @ApiOperation("删除菜单")
    @DeleteMapping("/delete/{id}")
    public void edit(@PathVariable Long id) {
        menuService.removeById(id);
    }

    @ApiOperation("下拉选择")
    @GetMapping("/select")
    public List<SelectDTO> select() {
        return menuService.select();
    }

    @GetMapping("/authorityTree")
    public List authorityTree() {
        return menuService.authorityTree();
    }

    @GetMapping("/findMenuByCurrentUser")
    public List<MenuTree> findMenuByCurrentUser() {
        return menuService.findMenuByUserId(AuthUtil.getUserId());
    }
}

package com.spring.cloud.auth.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.role.entity.Role;
import com.spring.cloud.auth.role.model.RoleDTO;
import com.spring.cloud.auth.role.model.RoleQueryCriteria;
import com.spring.cloud.auth.role.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;

/**
 * @author haozai
 * @date 2021/8/24 22:40
 */
@Api(value = "角色接口(role)")
@RequestMapping("/role")
@RestController
@AllArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @GetMapping("/page")
    public Page<Role> page(QueryPage queryPage, RoleQueryCriteria queryCriteria) {
        return roleService.page(new Page<>(queryPage.getCurrent(), queryPage.getSize()), QueryUtil.getPredicate(new QueryWrapper<Role>(),queryCriteria));
    }

    @ApiOperation("新增角色")
    @PostMapping("/add")
    public void add(@RequestBody @Validated RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        roleService.save(role);
    }

    @ApiOperation("编辑角色")
    @PutMapping("/edit")
    public void edit(@RequestBody @Validated RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        roleService.updateById(role);
    }
}

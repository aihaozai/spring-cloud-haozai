package com.spring.cloud.auth.system.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.cloud.auth.system.role.entity.RolePermission;
import com.spring.cloud.auth.system.role.model.RolePermissionDTO;
import com.spring.cloud.auth.system.role.model.RolePermissionQueryCriteria;
import com.spring.cloud.auth.system.role.service.IRolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.datasource.util.QueryUtil;
import java.util.List;

/**
 * @author haozai
 * @date 2021/9/12 8:58
 */
@Api(value = "角色权限接口(rolePermission)")
@RequestMapping("/rolePermission")
@RestController
@AllArgsConstructor
public class RolePermissionController {

    private final IRolePermissionService rolePermissionService;

    @GetMapping("/list")
    public List<RolePermission> list(RolePermissionQueryCriteria queryCriteria) {
        return rolePermissionService.list(QueryUtil.getPredicate(new QueryWrapper<>(),queryCriteria));
    }

    @ApiOperation("新增角色权限")
    @PostMapping("/add")
    public void add(@RequestBody @Validated RolePermissionDTO rolePermissionDTO) {
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(rolePermissionDTO,rolePermission);
        rolePermissionService.save(rolePermission);
    }

    @ApiOperation("删除角色权限")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        rolePermissionService.removeById(id);
    }
}

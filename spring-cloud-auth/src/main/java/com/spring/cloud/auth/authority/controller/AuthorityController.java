package com.spring.cloud.auth.authority.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.authority.entity.Authority;
import com.spring.cloud.auth.authority.model.AuthorityDTO;
import com.spring.cloud.auth.authority.model.AuthorityQueryCriteria;
import com.spring.cloud.auth.authority.service.IAuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.cloud.base.datasource.request.QueryPage;
import spring.cloud.base.datasource.util.QueryUtil;

import java.util.List;

/**
 * @author haozai
 * @date 2021/8/25 12:40
 */
@Api(value = "角色接口(authority)")
@RequestMapping("/authority")
@RestController
@AllArgsConstructor
public class AuthorityController {

    private final IAuthorityService authorityService;

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public List<Authority> list(AuthorityQueryCriteria queryCriteria) {
        return authorityService.list(QueryUtil.getPredicate(new QueryWrapper<>(),queryCriteria));
    }

    @ApiOperation("新增权限")
    @PostMapping("/add")
    public void add(@RequestBody @Validated AuthorityDTO authorityDTO) {
        Authority authority = new Authority();
        BeanUtils.copyProperties(authorityDTO,authority);
        authorityService.save(authority);
    }

    @ApiOperation("编辑权限")
    @PutMapping("/edit")
    public void edit(@RequestBody @Validated AuthorityDTO authorityDTO) {
        Authority authority = new Authority();
        BeanUtils.copyProperties(authorityDTO,authority);
        authorityService.updateById(authority);
    }

    @ApiOperation("删除权限")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        authorityService.removeById(id);
    }
}

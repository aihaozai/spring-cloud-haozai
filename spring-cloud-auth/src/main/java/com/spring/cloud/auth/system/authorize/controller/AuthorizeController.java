package com.spring.cloud.auth.system.authorize.controller;

import com.spring.cloud.auth.system.authorize.model.AuthorizeDTO;
import com.spring.cloud.auth.system.authorize.model.AuthorizeQueryCriteria;
import com.spring.cloud.auth.system.authorize.service.IAuthorizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author haozai
 * @date 2021/9/5 19:29
 */
@Api(value = "授权接口(authorize)")
@RequestMapping("/authorize")
@RestController
@AllArgsConstructor
public class AuthorizeController {

    private final IAuthorizeService authorizeService;

    @ApiOperation("列表查询")
    @GetMapping("/roleList")
    public AuthorizeDTO roleList(AuthorizeQueryCriteria queryCriteria) {
        return authorizeService.roleList(queryCriteria);
    }

    @ApiOperation("新增授权权限")
    @PostMapping("/add")
    public void add(@RequestBody @Validated AuthorizeDTO authorizeDTO) {
        authorizeService.add(authorizeDTO);
    }

}

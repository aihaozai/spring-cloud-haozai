package com.spring.cloud.auth.authorize.controller;

import com.spring.cloud.auth.authority.entity.Authority;
import com.spring.cloud.auth.authority.model.AuthorityDTO;
import com.spring.cloud.auth.authorize.model.AuthorizeDTO;
import com.spring.cloud.auth.authorize.service.IAuthorizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("新增授权权限")
    @PostMapping("/add")
    public void add(@RequestBody @Validated AuthorizeDTO authorizeDTO) {
        authorizeService.add(authorizeDTO);
    }

}

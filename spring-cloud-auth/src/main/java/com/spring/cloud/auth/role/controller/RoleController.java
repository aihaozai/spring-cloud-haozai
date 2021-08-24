package com.spring.cloud.auth.role.controller;

import com.spring.cloud.auth.role.service.IRoleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

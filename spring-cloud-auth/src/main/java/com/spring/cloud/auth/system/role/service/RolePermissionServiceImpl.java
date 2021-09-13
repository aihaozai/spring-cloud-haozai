package com.spring.cloud.auth.system.role.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.system.authority.entity.Authority;
import com.spring.cloud.auth.system.role.entity.RolePermission;
import com.spring.cloud.auth.system.role.mapper.RolePermissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.cloud.base.core.entity.AuthAuthority;

import java.util.List;

/**
 * @author haozai
 * @date 2021/9/8 22:40
 */
@Service
@AllArgsConstructor
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    private final RolePermissionMapper rolePermissionMapper;

    @Override
    public String findRoleByUserId(Long userId) {
        return rolePermissionMapper.findRoleByUserId(userId);
    }

    @Override
    public List<AuthAuthority> findAuthorityByUserId(Long userId) {
        return rolePermissionMapper.findAuthorityByUserId(userId);
    }
}

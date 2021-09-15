package com.spring.cloud.auth.system.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.system.role.entity.RolePermission;
import spring.cloud.base.core.model.AuthAuthority;

import java.util.List;

/**
 * @author haozai
 * @date 2021/9/8 22:40
 */
public interface IRolePermissionService extends IService<RolePermission> {


    /**
     *
     * 查找角色
     * @param userId 用户id
     * @return java.lang.String
     */
    String findRoleByUserId(Long userId);

    /**
     *
     * 查找权限
     * @param userId 用户id
     * @return java.util.List<spring.cloud.base.core.entity.AuthAuthority>
     */
    List<AuthAuthority> findAuthorityByUserId(Long userId);
}

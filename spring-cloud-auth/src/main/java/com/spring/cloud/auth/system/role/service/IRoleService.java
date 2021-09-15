package com.spring.cloud.auth.system.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.system.role.entity.Role;

import java.util.List;

/**
 * @author haozai
 * @date 2021/8/24 22:40
 */
public interface IRoleService extends IService<Role> {


    /**
     *
     * 查找角色
     * @param userId 用户id
     * @return java.util.List<com.spring.cloud.auth.system.role.entity.Role>
     */
    List<Role> findRoleByCurrentUser(Long userId);
}

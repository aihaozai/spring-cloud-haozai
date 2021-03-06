package com.spring.cloud.auth.system.role.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.system.role.entity.Role;
import com.spring.cloud.auth.system.role.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import spring.cloud.base.core.util.Assert;

import java.util.List;

/**
 * @author haozai
 * @date 2021/8/24 22:40
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final RoleMapper roleMapper;

    @SneakyThrows
    @Override
    public boolean save(Role entity) {
        int count = this.count(new LambdaQueryWrapper<Role>().eq(Role::getCode,entity.getCode()));
        Assert.thanZero(count,"该角色编码已存在");
        return super.save(entity);
    }

    @SneakyThrows
    @Override
    public boolean updateById(Role entity) {
        int count = this.count(new LambdaQueryWrapper<Role>().ne(Role::getId,entity.getId()).eq(Role::getCode,entity.getCode()));
        Assert.thanZero(count,"该角色编码已存在");
        return super.updateById(entity);
    }

    @Override
    public List<Role> findRoleByCurrentUser(Long userId) {
        return roleMapper.findRoleByCurrentUser(userId);
    }
}

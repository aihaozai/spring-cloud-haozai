package com.spring.cloud.auth.system.authority.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.system.authority.entity.Authority;
import com.spring.cloud.auth.system.authority.mapper.AuthorityMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import spring.cloud.base.core.util.Assert;

/**
 * @author haozai
 * @date 2021/8/25 12:40
 */
@Service
@AllArgsConstructor
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

    @SneakyThrows
    @Override
    public boolean save(Authority entity) {
        int count = this.count(new LambdaQueryWrapper<Authority>().ne(Authority::getMenuId,entity.getMenuId()).eq(Authority::getCode,entity.getCode()));
        Assert.thanZero(count,"该菜单编码已存在");
        return super.save(entity);
    }

    @SneakyThrows
    @Override
    public boolean updateById(Authority entity) {
        int count = this.count(new LambdaQueryWrapper<Authority>()
                .ne(Authority::getId,entity.getId())
                .ne(Authority::getMenuId,entity.getMenuId()).eq(Authority::getCode,entity.getCode()));
        Assert.thanZero(count,"该菜单编码已存在");
        return super.updateById(entity);
    }
}

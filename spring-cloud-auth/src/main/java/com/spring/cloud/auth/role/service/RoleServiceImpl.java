package com.spring.cloud.auth.role.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.role.entity.Role;
import com.spring.cloud.auth.role.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author haozai
 * @date 2021/8/24 22:40
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final RoleMapper menuMapper;

}

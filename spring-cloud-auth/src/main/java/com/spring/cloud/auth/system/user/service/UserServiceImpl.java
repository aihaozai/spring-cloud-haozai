package com.spring.cloud.auth.system.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.system.user.entity.User;
import com.spring.cloud.auth.system.user.mapper.UserMapper;
import com.spring.cloud.auth.system.user.model.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author haozai
 * @date 2021/3/24  22:40
 */

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

    private final UserMapper userMapper;

    @Override
    public Page<UserVO> selectUserPage(Page<UserVO> page, QueryWrapper queryWrapper) {
        return this.baseMapper.selectUserPage(page,queryWrapper);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}

package com.spring.cloud.auth.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.user.entity.User;
import com.spring.cloud.auth.user.mapper.UserMapper;
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

    public IPage<User> selectUserPage(Page<User> page) {
        return this.baseMapper.selectPage(page,null);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}

package com.spring.cloud.auth.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.user.entity.User;
import com.spring.cloud.auth.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author haozai
 * @date 2021/3/24  22:40
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

    public IPage<User> selectUserPage(Page<User> page) {
        return this.baseMapper.selectPage(page,null);
    }

}

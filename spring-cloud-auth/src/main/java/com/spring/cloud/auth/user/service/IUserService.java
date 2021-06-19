package com.spring.cloud.auth.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.user.entity.User;

/**
 * @author haozai
 * @date 2021/4/30  11:19
 */
public interface IUserService extends IService<User> {
    /**
     * 根据用户名称查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}

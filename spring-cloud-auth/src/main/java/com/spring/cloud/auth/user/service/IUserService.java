package com.spring.cloud.auth.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.user.entity.User;
import com.spring.cloud.auth.user.model.UserVO;

/**
 * @author haozai
 * @date 2021/4/30  11:19
 */
public interface IUserService extends IService<User> {

    /**
     * 用户分页查询
     * @param page 分页
     * @param queryWrapper 构造器
     * @return
     */
    Page<UserVO> selectUserPage(Page<UserVO> page, QueryWrapper queryWrapper);

    /**
     * 根据用户名称查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);


}

package com.spring.cloud.auth.system.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.system.user.entity.User;
import com.spring.cloud.auth.system.user.model.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author haozai
 * @date 2021/3/24  22:40
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户分页查询
     * @param page 分页
     * @param queryWrapper 构造器
     * @return
     */
    Page<UserVO> selectUserPage(Page<UserVO> page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    /**
     * 根据用户名称查询用户
     * @param username
     * @return
     */
    @Select("select id,account,password,username,account_non_expired,account_non_locked,credentials_non_expired,enabled,avatar from user where username = #{username}")
    User findUserByUsername(String username);
}
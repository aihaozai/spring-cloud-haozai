package com.spring.cloud.auth.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.user.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author haozai
 * @date 2021/3/24  22:40
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户分页查询
     * @param page
     * @return
     */
    IPage<User> selectUserPage(Page<User> page);

    /**
     * 根据用户名称查询用户
     * @param username
     * @return
     */
    @Select("select id,username,password from user where username = #{username}")
    User findUserByUsername(String username);
}

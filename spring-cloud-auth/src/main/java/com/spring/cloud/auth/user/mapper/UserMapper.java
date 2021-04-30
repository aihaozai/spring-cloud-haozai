package com.spring.cloud.auth.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.cloud.auth.user.entity.User;

/**
 * @author haozai
 * @date 2021/3/24  22:40
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectUserPage(Page<User> page);
}

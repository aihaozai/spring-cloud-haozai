package com.spring.cloud.auth.authorize.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.auth.authorize.entity.Authorize;
import com.spring.cloud.auth.authorize.model.AuthorizeDTO;

/**
 * @author haozai
 * @date 2021/9/5 19:29
 */
public interface IAuthorizeService extends IService<Authorize> {


    /**
     *
     * 新增
     * @param authorizeDTO 授权权限
     */
    void add(AuthorizeDTO authorizeDTO);
}

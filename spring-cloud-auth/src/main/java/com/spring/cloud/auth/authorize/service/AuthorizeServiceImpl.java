package com.spring.cloud.auth.authorize.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.authorize.entity.Authorize;
import com.spring.cloud.auth.authorize.mapper.AuthorizeMapper;
import com.spring.cloud.auth.authorize.model.AuthorizeDTO;
import com.spring.cloud.auth.constant.AuthorizeConstant;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haozai
 * @date 2021/9/5 19:29
 */
@Service
@RequiredArgsConstructor
public class AuthorizeServiceImpl extends ServiceImpl<AuthorizeMapper, Authorize> implements IAuthorizeService {


    @Override
    public void add(AuthorizeDTO authorizeDTO) {
        super.remove(new LambdaQueryWrapper<Authorize>().eq(Authorize::getRoleId,authorizeDTO.getRoleId()));
        List<Authorize> addList = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(authorizeDTO.getMenuList())){
            authorizeDTO.getMenuList().forEach(a-> addList.add(Authorize.builder().type(AuthorizeConstant.MENU).roleId(authorizeDTO.getRoleId()).typeId(a).build()));
        }
        if(CollectionUtil.isNotEmpty(authorizeDTO.getAuthorityList())){
            authorizeDTO.getAuthorityList().forEach(a-> addList.add(Authorize.builder().type(AuthorizeConstant.AUTHORITY).roleId(authorizeDTO.getRoleId()).typeId(a).build()));
        }
        if(CollectionUtil.isNotEmpty(addList)){
            super.saveBatch(addList);
        }
    }
}

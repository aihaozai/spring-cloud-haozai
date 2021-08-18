package com.spring.cloud.auth.menu.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.auth.menu.entity.Menu;
import com.spring.cloud.auth.menu.mapper.MenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author haozai
 * @date 2021/8/18  14:31
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


}

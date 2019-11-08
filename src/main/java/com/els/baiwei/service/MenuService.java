package com.els.baiwei.service;

import com.els.baiwei.mapper.MenuMapper;
import com.els.baiwei.model.Hr;
import com.els.baiwei.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/7 17:09
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenuByHrId() {
        return menuMapper.getMenuByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}

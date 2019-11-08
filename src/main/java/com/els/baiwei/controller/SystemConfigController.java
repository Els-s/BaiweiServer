package com.els.baiwei.controller;

import com.els.baiwei.model.Menu;
import com.els.baiwei.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/7 17:08
 */
@RestController
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    @GetMapping("/system/config/menus")
    public List<Menu> getMenuByHrId(){
        return  menuService.getMenuByHrId();
    }
}

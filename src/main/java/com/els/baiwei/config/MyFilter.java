package com.els.baiwei.config;

import com.els.baiwei.model.Menu;
import com.els.baiwei.model.Role;
import com.els.baiwei.service.MenuService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Despt: 请求地址所需角色
 * @Author: Els-s
 * @Time: 2019/11/7 20:42
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

//ant地址通配符
    AntPathMatcher antPathMatcher=new AntPathMatcher();


 /**
  * 功能描述:
  * @param:[o]  请求参数中可以提出去当前请求地址
  * @return:Collection<org.springframework.security.access.ConfigAttribute> 返回值为当前请求所需要的角色
  * @Author:Els-s
  * @Date:
  */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //请求地址
        String url = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenus = menuService.getAllMenus();
        for (Menu menu : allMenus) {
            if (antPathMatcher.match(menu.getUrl(),url)){
                List<Role> roles = menu.getRoles();
                String[] ss=new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    ss[i]=roles.get(i).getName();
                }
                return SecurityConfig.createList(ss);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

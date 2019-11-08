package com.els.baiwei.service;

import com.els.baiwei.mapper.HrMapper;
import com.els.baiwei.mapper.RoleMapper;
import com.els.baiwei.model.Hr;
import com.els.baiwei.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/6 9:41
 */
@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Autowired
    RoleMapper roleMapper;


    @Override
    /**
     * 功能描述: 登录用户信息写入
     * @param:[s]
     * @return:org.springframework.security.core.userdetails.UserDetails
     * @Author:Els-s
     * @Date: 2019/11/7 23:31
     */
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(s);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名未找到!");
        }
        hr.setRoles(roleMapper.getRoleByHrId(hr.getId()));
        return hr;
    }
}

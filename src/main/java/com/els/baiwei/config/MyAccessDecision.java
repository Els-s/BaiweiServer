package com.els.baiwei.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Despt:  当前用户具有的角色
 * @Author: Els-s
 * @Time: 2019/11/7 22:48
 */
@Component
public class MyAccessDecision implements AccessDecisionManager {

    /**
     * 功能描述: 核心代码
     * @param:[authentication, object, configAttributes]
     * @return:void
     * @Author:Els-s
     * @Date: 2019/11/7 22:50
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //当前用户所具有的角色
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //configAttributes放的需要的角色
        for (ConfigAttribute attribute : configAttributes) {
            if (attribute.getAttribute().equals("ROLE_LOGIN")) {
                if (authentication instanceof UsernamePasswordAuthenticationToken) {
                        return;
                }else {
                    throw new AccessDeniedException("用户权限不足,请联系管理员");
                }
            }
            for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals(attribute.getAttribute())){
                        return;
                    }
            }
        }
        throw new AccessDeniedException("用户权限不足,请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

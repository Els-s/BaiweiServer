package com.els.baiwei.config;

import com.els.baiwei.model.RespBean;
import com.els.baiwei.service.HrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Despt: Security封装对前端数据处理
 * @Author: Els-s
 * @Time: 2019/11/6 9:46
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;

    @Autowired
    MyFilter myFilter;

    @Autowired
    MyAccessDecision myAccessDecision;

    /*http请求*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//     在configure(HttpSecurity http方法中，通过withObjectPostProcessor
// 将刚刚创建的UrlFilterInvocationSecurityMetadataSource和UrlAccessDecisionManager注入进来。
// 到时候，请求都会经过刚才的过滤器（除了configure(WebSecurity web)方法忽略的请求）。
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myFilter);
                        o.setAccessDecisionManager(myAccessDecision);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        String s = new ObjectMapper().writeValueAsString(RespBean.ok("登录成功!", authentication.getPrincipal()));
                        PrintWriter writer = response.getWriter();
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        RespBean respBean = RespBean.error("登录失败!");
                        String s = new ObjectMapper().writeValueAsString(respBean);
                        if (e instanceof BadCredentialsException) {
                            respBean.setMsg("用户名或密码错误!");
                        }
                        if (e instanceof DisabledException) {
                            respBean.setMsg("账户被禁用!");
                        }
                        PrintWriter writer = response.getWriter();
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
//               注销操作
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        String s = new ObjectMapper().writeValueAsString(RespBean.ok("注销成功!"));
                        PrintWriter writer = response.getWriter();
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .and()
                .csrf().disable()

                /*处理跨域问题*/
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    //前端重定向时不返回地址
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setStatus(401);
                response.setContentType("application/json;charset=utf-8");
                String s = new ObjectMapper().writeValueAsString(RespBean.error("尚未登录,请登录!"));
                PrintWriter writer = response.getWriter();
                writer.write(s);
                writer.flush();
                writer.close();
            }
        })
        ;

    }

    /*确认用户*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    /*密码类型*/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

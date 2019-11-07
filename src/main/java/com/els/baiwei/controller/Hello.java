package com.els.baiwei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/6 10:29
 */
@RestController
public class Hello {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}

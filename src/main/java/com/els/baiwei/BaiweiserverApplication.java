package com.els.baiwei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.els.baiwei.mapper")
public class BaiweiserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiweiserverApplication.class, args);
    }

}

package com.els.baiwei.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Despt:  返回数据接口
 * @Author: Els-s
 * @Time: 2019/11/6 9:56
 */

@Getter
@Setter
@ToString
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespBean ok(String msg,Object obj){
        return new RespBean(200,msg,obj);
    }
    public static RespBean ok(String msg){
        return new RespBean(200,msg,null);
    }

    public static RespBean error(String msg,Object obj){
        return new RespBean(500,msg,obj);
    }
    public static RespBean error(String msg){
        return new RespBean(500,msg,null);
    }

    private RespBean() {
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
}

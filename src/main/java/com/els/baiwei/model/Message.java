package com.els.baiwei.model;

import java.io.Serializable;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/12 16:22
 */
public class Message implements Serializable {
    private String from;
    private String to;
    private String msg;

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

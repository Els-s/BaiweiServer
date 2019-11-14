package com.els.baiwei.model;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/8 10:46
 */
public class RespPageBean {

    private Integer total;

    private List<?> data;

    public void setData(List<?> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }


}

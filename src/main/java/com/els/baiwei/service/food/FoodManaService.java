package com.els.baiwei.service.food;

import com.els.baiwei.mapper.FoodMapper;
import com.els.baiwei.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/12 10:37
 */
@Service
public class FoodManaService {

    @Autowired
    FoodMapper foodMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RespPageBean getAllFood(Integer currentPage, Integer pageSize, String keywords) {
        RespPageBean respPageBean=new RespPageBean();
        Integer PageIndex=null;
        if (currentPage != null && pageSize != null) {
            PageIndex=(currentPage-1) * pageSize;
        }
        respPageBean.setData(foodMapper.getAllFood(PageIndex,pageSize,keywords));
        respPageBean.setTotal(foodMapper.getTotalCount(keywords));
        return respPageBean;
    }
}

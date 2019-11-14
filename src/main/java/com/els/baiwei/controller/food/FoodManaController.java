package com.els.baiwei.controller.food;

import com.els.baiwei.model.Food;
import com.els.baiwei.model.RespPageBean;
import com.els.baiwei.service.food.FoodManaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/12 10:17
 */
@RestController
@RequestMapping("/food/mana")
public class FoodManaController {

    @Autowired
    FoodManaService foodManaService;

    @GetMapping("/")
    public RespPageBean getAllFood(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize, String keywords){
        return foodManaService.getAllFood(currentPage,pageSize,keywords);
    }
}

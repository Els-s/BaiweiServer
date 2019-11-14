package com.els.baiwei.mapper;

import com.els.baiwei.model.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    List<Food> getAllFood(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize,@Param("keywords") String keywords);

    Integer getTotalCount(String keywords);
}
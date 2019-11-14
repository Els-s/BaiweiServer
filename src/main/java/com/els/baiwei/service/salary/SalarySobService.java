package com.els.baiwei.service.salary;

import com.els.baiwei.mapper.SalaryMapper;
import com.els.baiwei.model.RespBean;
import com.els.baiwei.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/11 14:47
 */
@Service
public class SalarySobService {

    @Autowired
    SalaryMapper salaryMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Salary> getAllSalary() {
        return salaryMapper.getAllSalary();
    }
    @Transactional
    public RespBean addSalary(Salary salary) {
        int i = salaryMapper.insertSelective(salary);
        if (i == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加成功!");
    }
    @Transactional
    public RespBean updateSalary(Salary salary) {
        if (salaryMapper.updateByPrimaryKeySelective(salary)==1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @Transactional
    public RespBean deleteSalary(Integer id) {
        if (salaryMapper.deleteByPrimaryKey(id)==1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}

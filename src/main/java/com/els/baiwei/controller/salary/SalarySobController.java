package com.els.baiwei.controller.salary;

import com.els.baiwei.model.RespBean;
import com.els.baiwei.model.Salary;
import com.els.baiwei.service.salary.SalarySobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/11 14:45
 */
@RestController
@RequestMapping("/salary/sob")
public class SalarySobController {

    @Autowired
    SalarySobService salarySobService;

    @GetMapping("/")
    public List<Salary> getAllSalary(){
        return salarySobService.getAllSalary();
    }

    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        return salarySobService.addSalary(salary);
    }

    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary){
        return salarySobService.updateSalary(salary);
    }
    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable Integer id){
        return salarySobService.deleteSalary(id);
    }
}

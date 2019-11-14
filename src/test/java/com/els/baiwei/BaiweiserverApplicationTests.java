package com.els.baiwei;

import com.els.baiwei.mapper.EmployeeMapper;
import com.els.baiwei.model.Employee;
import com.els.baiwei.service.emp.EmpBasicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaiweiserverApplicationTests {

    @Test
    void contextLoads() {
        System.out.format("Current ID is: %d and name is: %s",5,"lik");
        Integer i=2;
        System.out.println(i.equals(2));
    }


    @Autowired
    EmployeeMapper mapper;

    @Test
    void test1(){
        Employee employee = new Employee();
        employee.setAddress("sz");
        employee.setName("jiss");
        employee.setGender("男");
        employee.setPhone("123456789");
        long start = System.currentTimeMillis();
        mapper.insert(employee);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        long start1 = System.currentTimeMillis();
        mapper.insertSelective(employee);
        long end1 = System.currentTimeMillis();
        System.out.println(end1-start1);
    }

}

package com.els.baiwei.service.emp;

import com.els.baiwei.mapper.DepartmentMapper;
import com.els.baiwei.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/9 9:17
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;



    public List<Department> getAllDep() {
        return departmentMapper.getAllDep(-1);
    }
}

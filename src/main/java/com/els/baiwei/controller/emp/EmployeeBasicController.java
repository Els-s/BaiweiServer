package com.els.baiwei.controller.emp;

import com.els.baiwei.model.*;
import com.els.baiwei.service.emp.*;
import com.els.baiwei.utils.POIUtils;
import com.els.baiwei.utils.POIUtils1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Despt: 基础表查询
 * @Author: Els-s
 * @Time: 2019/11/8 15:01
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeBasicController {

    @Autowired
    EmpBasicService empBasicService;

    @Autowired
    NationService nationService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;


    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize, String keywords) {
        return empBasicService.getEmployeeByPage(currentPage, pageSize, keywords);
    }

    @DeleteMapping("/{id}") //必须要@PathVariable占位符
    public RespBean deleteEmployeeById(@PathVariable Integer id) {
        return empBasicService.deleteEmployeeById(id);
    }

    /*添加*/
    @PostMapping("/") //@RequestBody表示返回数据为json类型
    public RespBean addEmployee(@RequestBody Employee employee) {
        return empBasicService.addEmployee(employee);
    }

    /*更新*/
    @PutMapping("/") //@RequestBody表示返回数据为json类型
    public RespBean updateEmployee(@RequestBody Employee employee) {
        return empBasicService.updateEmployee(employee);
    }

    /*获取民族*/
    @GetMapping("/nations")
    public List<Nation> getAllNation() {
        return nationService.getAllNation();
    }

    /*获取职称*/
    @GetMapping("/jobLevels")
    public List<JObLevel> getJobLevels() {
        return jobLevelService.getJobLevels();
    }

    /*获取政治面貌*/
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getPoliticsstatus() {
        return politicsstatusService.getPoliticsstatus();
    }

    /*获取职位*/
    @GetMapping("/positions")
    public List<Position> getPositions() {
        return positionService.getPositions();
    }

    /*查询部门*/
    @GetMapping("/dep")
    public List<Department> getAllDep() {
        return departmentService.getAllDep();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        return POIUtils1.emp2Exel((List<Employee>) empBasicService.getEmployeeByPage(null, null, null).getData());
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils1.importData(file);
//        return empBasicService.addAllEmp(list);
        return empBasicService.addAllEmp2(list);
    }
}

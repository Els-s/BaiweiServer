package com.els.baiwei.mapper;

import com.els.baiwei.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmployeeByPage(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize,@Param("keywords") String keywords);

    Integer selectEmployeeCount(String keywords);

    void addEmps(@Param("emps") List<Employee> emps);

    void insertEmp(Employee employee);
}
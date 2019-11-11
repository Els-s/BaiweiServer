package com.els.baiwei.service.emp;

import com.els.baiwei.mapper.EmployeeMapper;
import com.els.baiwei.model.Employee;
import com.els.baiwei.model.RespBean;
import com.els.baiwei.model.RespPageBean;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/8 15:03
 */
@Service
public class EmpBasicService {

    @Resource
    EmployeeMapper employeeMapper;

    public RespPageBean getEmployeeByPage(Integer currentPage, Integer pageSize, String keywords) {
        RespPageBean respPageBean = new RespPageBean();
        Integer pageIndex = null;
        if (currentPage != null && pageSize != null) {
            pageIndex = (currentPage - 1) * pageSize;
        }
        respPageBean.setData(employeeMapper.getEmployeeByPage(pageIndex, pageSize, keywords));
        respPageBean.setTotal(employeeMapper.selectEmployeeCount(keywords));
        return respPageBean;
    }

    public RespBean deleteEmployeeById(Integer id) {
        int i = employeeMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    public RespBean addEmployee(Employee employee) {
        int i = employeeMapper.insertSelective(employee);
        if (i == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败");
    }

    public RespBean updateEmployee(Employee employee) {
        int i = employeeMapper.updateByPrimaryKeySelective(employee);
        if (i == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败");
    }

    /*sql批量导入方式*/
    public RespBean addAllEmp(List<Employee> emps) {
        long startTime = System.currentTimeMillis();
        try {
            employeeMapper.addEmps(emps);
            long endTime = System.currentTimeMillis();
            System.out.println("sql模式====>" + (endTime - startTime));
            return RespBean.ok("导入成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("导入失败!");
        }
    }

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    /*mybatis batch批量导入方式*/
    public RespBean addAllEmp2(List<Employee> emps) {
        SqlSessionFactory sessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        //这里修改执行器的类型
        SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        long startTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < emps.size(); i++) {
//                手写方法用时    1314
//                employeeMapper.insertEmp(emps.get(i));
//                自动改变方法用时  1385
                employeeMapper.insertSelective(emps.get(i));
            }
            session.commit();
            long endTime = System.currentTimeMillis();
            /**
             * BATCH方式用时：1385   1314
             * 普通方式用时：sql模式====>102
             */
            System.out.println("用时：" + (endTime - startTime));
            return RespBean.error("导入失败!");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("导入成功!");
        } finally {
            session.close();
        }
    }
}

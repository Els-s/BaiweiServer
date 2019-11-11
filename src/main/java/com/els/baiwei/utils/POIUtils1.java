package com.els.baiwei.utils;

import com.els.baiwei.model.Employee;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Despt:  Exel文件上传下载
 * @Author: Els-s
 * @Time: 2019/11/9 19:25
 */
public class POIUtils1 {


    public static ResponseEntity<byte[]> emp2Exel(List<Employee> employees) {
//        System.out.println("employees====>"+employees.size());
        //1.创建一个Exel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建文件属性
        workbook.createInformationProperties();
        //3.获取文件属性
        SummaryInformation summaryInfo = workbook.getSummaryInformation();
        summaryInfo.setTitle("员工信息表");
        summaryInfo.setAuthor("els");
//        summaryInfo.setCharCount();
        summaryInfo.setComments("备注信息");
        summaryInfo.setSubject("主题");
//        4.配置文档的属性
        DocumentSummaryInformation documentnformation = workbook.getDocumentSummaryInformation();
        documentnformation.setCompany("abs");
        documentnformation.setManager("管理员");
        documentnformation.setCategory("文档分类");
//       5.创建sheet表单
        HSSFSheet sheet = workbook.createSheet("员工信息表单");
        //6.创建行,参数表示第几行
        HSSFRow row1= sheet.createRow(0);
        //7.在行中创建列
        HSSFCell cell0 = row1.createCell(0);
        ((HSSFCell) cell0).setCellValue("用户编号");
        HSSFCell cell1 = row1.createCell(1);
        ((HSSFCell) cell1).setCellValue("用户名");
        HSSFCell cell2 = row1.createCell(2);
        ((HSSFCell) cell2).setCellValue("用户性别");
        HSSFCell cell3 = row1.createCell(3);
        ((HSSFCell) cell3).setCellValue("手机");
        HSSFCell cell4 = row1.createCell(4);
        ((HSSFCell) cell4).setCellValue("地址");
//        System.out.println("cell4.getDateCellValue=====>"+cell4.getStringCellValue());
        //8.向Exel写入数据
        for (int i = 1; i <= employees.size(); i++) {
            Employee e=employees.get(i-1);
            HSSFRow row = sheet.createRow(i);
            HSSFCell c0 = row.createCell(0);
            c0.setCellValue(e.getId());
            HSSFCell c1 = row.createCell(1);
            c1.setCellValue(e.getName());
            HSSFCell c2 = row.createCell(2);
            c2.setCellValue(e.getGender());
            HSSFCell c3 = row.createCell(3);
            c3.setCellValue(e.getPhone());
            HSSFCell c4 = row.createCell(4);
            c4.setCellValue(e.getAddress());
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        HttpHeaders header=new HttpHeaders();
        try {
            workbook.write(stream);
//        设定文件名
            header.setContentDispositionFormData("attachment",new String("员工信息表.xls".getBytes("UTF-8"),"ISO-8859-1"));
            //设置响应头信息
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(stream.toByteArray(), header, HttpStatus.CREATED);
    }

    /*导入数据*/
    public static List<Employee> importData(MultipartFile file) {
        List<Employee> list=new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
             System.out.println("getSheetName=====>"+sheet.getSheetName());
            //获取总行数
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < numberOfRows; i++) {
                HSSFRow row = sheet.getRow(i);
                HSSFCell c0 = row.getCell(0);
                HSSFCell c1 = row.getCell(1);
                HSSFCell c2 = row.getCell(2);
                HSSFCell c3 = row.getCell(3);
                HSSFCell c4 = row.getCell(4);
                Employee employee = new Employee();
                employee.setName(c1.getStringCellValue());
                employee.setGender(c2.getStringCellValue());
                employee.setPhone(c3.getStringCellValue());
                employee.setAddress(c4.getStringCellValue());
                list.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

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

public class POIUtils {
    public static ResponseEntity<byte[]> emp2Excel(List<Employee> employees) {
        //1.创建一个 Excel 文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建文件属性
        workbook.createInformationProperties();
        //3.获取文件属性
        SummaryInformation summaryInformation = workbook.getSummaryInformation();
        //4.配置文档属性
        summaryInformation.setTitle("员工信息表");
        summaryInformation.setAuthor("javaboy");
        summaryInformation.setComments("我的备注信息");
        summaryInformation.setSubject("这是我的主题");
        DocumentSummaryInformation documentSummaryInformation = workbook.getDocumentSummaryInformation();
        documentSummaryInformation.setCompany("这是我的公司名");
        documentSummaryInformation.setManager("管理员");
        documentSummaryInformation.setCategory("文档分类");
        //5.创建表单
        HSSFSheet sheet = workbook.createSheet("员工信息表");
        //6.创建行，参数表示第几行
        HSSFRow r0 = sheet.createRow(0);
        //7.在行中创建列
        HSSFCell c0 = r0.createCell(0);
        ((HSSFCell) c0).setCellValue("用户编号");
        HSSFCell c1 = r0.createCell(1);
        ((HSSFCell) c1).setCellValue("用户名");
        HSSFCell c2 = r0.createCell(2);
        ((HSSFCell) c2).setCellValue("用户性别");
        HSSFCell c3 = r0.createCell(3);
        ((HSSFCell) c3).setCellValue("手机");
        HSSFCell c4 = r0.createCell(4);
        ((HSSFCell) c4).setCellValue("地址");
        //8.向 Excel 中写入数据
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            HSSFRow row = sheet.createRow(i+1);
            HSSFCell cell0 = row.createCell(0);
            ((HSSFCell) cell0).setCellValue(e.getId());
            HSSFCell cell1 = row.createCell(1);
            ((HSSFCell) cell1).setCellValue(e.getName());
            HSSFCell cell2 = row.createCell(2);
            ((HSSFCell) cell2).setCellValue(e.getGender());
            HSSFCell cell3 = row.createCell(3);
            ((HSSFCell) cell3).setCellValue(e.getPhone());
            HSSFCell cell4 = row.createCell(4);
            ((HSSFCell) cell4).setCellValue(e.getAddress());
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            workbook.write(baos);
            headers.setContentDispositionFormData("attachment", new String("员工信息表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

//    public static List<Employee> excel2Emp(MultipartFile file) {
//        List<Employee> list = new ArrayList<>();
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
//            HSSFSheet sheet = workbook.getSheetAt(0);
//            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//            for (int i = 1; i < physicalNumberOfRows; i++) {
//                HSSFRow r = sheet.getRow(i );
//                HSSFCell c0 = r.getCell(0);
//                HSSFCell c1 = r.getCell(1);
//                HSSFCell c2 = r.getCell(2);
//                HSSFCell c3 = r.getCell(3);
//                HSSFCell c4 = r.getCell(4);
//                Employee e = new Employee();
//                e.setName(c1.getStringCellValue());
//                e.setGender(c2.getStringCellValue());
//                e.setPhone(c3.getStringCellValue());
//                e.setAddress(c4.getStringCellValue());
//                list.add(e);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}

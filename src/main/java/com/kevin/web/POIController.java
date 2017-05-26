package com.kevin.web;

import com.kevin.domain.Person;
import com.kevin.domain.PersonRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * Created by min on 2017/5/25.
 */
@RestController
public class POIController {
    @Autowired
    private PersonRepository personRepository;

    @ApiOperation(value = "导出excel")
    @GetMapping(value = "/poidownload")
    public void getPOI(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
         String headers[] = { " ","学号", "姓名", "性别", "年龄", "专业", "班级", "入校年份", "毕业年份", "就业单位", "所在城市",
                "联系方式", "电子邮箱"};
         //response.setContentType("text/html;charset=utf-8");
         List<Person> personList = personRepository.findAll();
         Workbook wb = new HSSFWorkbook();
        int rowIndex = 0; // 第一行
        Sheet sheet = wb.createSheet(); // 创建sheet页
        Row row = sheet.createRow(rowIndex++);
        // 创建标题
        for (int i = 0; i < headers.length; i++) {
            row.createCell(i).setCellValue(headers[i]);
        }
        // 导出数据库中的数据
        for (Person person : personList) {
            row = sheet.createRow(rowIndex++);
            if (person.getStudentNum()!=null){
                row.createCell(1).setCellValue(person.getStudentNum());
            }else{
                row.createCell(1).setCellValue("");
            }
            if (person.getName()!=null){
                row.createCell(2).setCellValue(person.getName());
            }else{
                row.createCell(2).setCellValue("");
            }
            if (person.getSex()!=null){
                row.createCell(3).setCellValue(person.getSex());
            }else{
                row.createCell(3).setCellValue("");
            }
            if (person.getAge()!=null){
                row.createCell(4).setCellValue(person.getAge());
            }else{
                row.createCell(4).setCellValue("");
            }
            if (person.getMajor()!=null){
                row.createCell(5).setCellValue(person.getMajor());
            }else{
                row.createCell(5).setCellValue("");
            }
            if (person.getClasses()!=null){
                row.createCell(6).setCellValue(person.getClasses());
            }else{
                row.createCell(6).setCellValue("");
            }
            if (person.getEnterYear()!=null){
                row.createCell(7).setCellValue(person.getEnterYear());
            }else{
                row.createCell(7).setCellValue("");
            }
            if (person.getGraduationYear()!=null){
                row.createCell(8).setCellValue(person.getGraduationYear());
            }else{
                row.createCell(8).setCellValue("");
            }
            if (person.getWorkUnit()!=null){
                row.createCell(9).setCellValue(person.getWorkUnit());
            }else{
                row.createCell(9).setCellValue("");
            }
            if (person.getCity()!=null){
                row.createCell(10).setCellValue(person.getCity());
            }else{
                row.createCell(10).setCellValue("");
            }
            if (person.getPhoneNumber()!=null){
                row.createCell(11).setCellValue(person.getPhoneNumber());
            }else{
                row.createCell(11).setCellValue("");
            }
            if (person.getEmail()!=null){
                row.createCell(12).setCellValue(person.getEmail());
            }else{
                row.createCell(12).setCellValue("");
            }
        }

        OutputStream out = new FileOutputStream(this.getClass().getClassLoader().getResource("").toURI().getPath()+"ImportContacts.xls");
        wb.write(out);// 进行输出，下载到本地
        out.flush();
        out.close();

        String filePath = this.getClass().getClassLoader().getResource("").toURI().getPath()
                + "/ImportContacts.xls";  //文件在项目中的路径
        File outfile = new File(filePath);
        String filename = outfile.getName();// 获取文件名称
        InputStream fis = new BufferedInputStream(new FileInputStream(
                filePath));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);  //读取文件流
        fis.close();
        response.reset();  //重置结果集
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=ImportContacts.xls");
        //获取返回体输出权
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        os.write(buffer); // 输出文件
        os.flush();
        os.close();


    }
}

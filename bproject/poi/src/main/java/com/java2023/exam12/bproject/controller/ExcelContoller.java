package com.java2023.exam12.bproject.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;

import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.java2023.exam12.bproject.entity.ReadData;
import com.java2023.exam12.bproject.entity.Salary;
import com.java2023.exam12.bproject.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("excel")
public class ExcelContoller {
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName= URLEncoder.encode("测试","UTF-8").replaceAll("\\+","%20");
        response.setHeader("Content-disposition","attachment;filename*=utf-8''"+fileName+".xlsx");

        //构造数据
        List<Student> list = new ArrayList<Student>();
        for (int i=0;i<10;i++){
            Student student = new Student();
            student.setId(i+1);
            student.setName("学生"+i);
            student.setScore(i+60);
            list.add(student);
        }

        EasyExcel.write(response.getOutputStream() //输出的outputstream
                        ,Student.class)
                .sheet("模板1")
                .doWrite(list);
    }
    @PostMapping("upload")
    public String upload(MultipartFile file) throws IOException {
        //1,获取输入流
        InputStream is =file.getInputStream();

        //2，输出文件
        String fileName = "e:/"+ "simpleFill" + System.currentTimeMillis() + ".xlsx";

        //3,根据数据填充模板
        EasyExcel.write(fileName).withTemplate(is)
                .sheet().doFill(new Salary("张三",10000,50));
        //2，创建读取器
        ExcelReader excelReader = EasyExcel.read(is).build();

        List<ReadData> list = new ArrayList<ReadData>();
        //3，创建读取的sheet的对象
        ReadSheet readSheet = EasyExcel.readSheet(0).head(ReadData.class)
                .registerReadListener(new ReadListener<ReadData>() {
                    @Override
                    public void invoke(ReadData readData, AnalysisContext analysisContext) {
                        list.add(readData);
                    }

                    //数据读取完毕的方法
                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        System.out.println("读取数据完毕");
                        list.forEach(readData -> {
                            System.out.println(readData.toString());
                        });
                    }

                }).build();

        //4，数据数据
        excelReader.read(readSheet);

        //5，读取完毕
        excelReader.finish();

        return "excel上传成功";
    }
}

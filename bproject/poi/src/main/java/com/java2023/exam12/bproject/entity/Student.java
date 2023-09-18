package com.java2023.exam12.bproject.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student {
    @ExcelProperty("学生编号")
    Integer id;
    @ExcelProperty("学生姓名")
    String name;
    @ExcelProperty("学生成绩")
    Integer score;
}
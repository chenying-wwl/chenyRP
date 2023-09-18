package com.java2023.exam12.bproject.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class ReadData {
    @ExcelProperty("标题")
    Integer title;
    @ExcelProperty("日期")
    Date dateT;
    @ExcelProperty("数字")
    Integer count;
}

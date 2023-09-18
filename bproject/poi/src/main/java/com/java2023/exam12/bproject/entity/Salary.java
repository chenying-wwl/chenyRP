package com.java2023.exam12.bproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//对象中的属性和模板的变量要一一对应
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    String name;

    Integer base;

    Integer tax;


}

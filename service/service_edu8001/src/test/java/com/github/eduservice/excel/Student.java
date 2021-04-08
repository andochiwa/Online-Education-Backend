package com.github.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@Data
public class Student {

    @ExcelProperty("学生编号")
    private Integer id;

    @ExcelProperty("学生姓名")
    private String name;
}

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

    @ExcelProperty(value = "学生编号", index = 0)
    private Integer id;

    @ExcelProperty(value = "学生姓名", index = 1)
    private String name;
}

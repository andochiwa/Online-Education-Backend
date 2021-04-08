package com.github.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@Data
public class ExcelSubjectData {

    @ExcelProperty(index = 0)
    private String firstSubjectName;

    @ExcelProperty(index = 1)
    private String secondSubjectName;
}

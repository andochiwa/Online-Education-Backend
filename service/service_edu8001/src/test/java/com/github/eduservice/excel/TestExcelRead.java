package com.github.eduservice.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@SpringBootTest
public class TestExcelRead {

    @Test
    void test() {
        String filePath = "D:\\write.xlsx";

        EasyExcel.read(filePath, Student.class, new ExcelListener()).sheet().doRead();
    }
}

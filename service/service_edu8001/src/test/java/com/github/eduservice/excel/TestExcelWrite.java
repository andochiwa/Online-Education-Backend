package com.github.eduservice.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@SpringBootTest
public class TestExcelWrite {

    private List<Student> list = new ArrayList<>();

    @BeforeEach
    void initList() {
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("q" + i);
            list.add(student);
        }
    }

    @Test
    void test() {
        String filePath = "D:\\write.xlsx";

        EasyExcel.write(filePath, Student.class).sheet("学生列表").doWrite(this.list);
    }

}

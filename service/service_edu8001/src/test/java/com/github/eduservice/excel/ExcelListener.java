package com.github.eduservice.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
public class ExcelListener extends AnalysisEventListener<Student> {
    // 一行行读取excel里的内容
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("数据 " + student);
    }

    // 读取表头的内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头 " + headMap);
    }

    // 读取完成后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

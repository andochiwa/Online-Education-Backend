package com.github.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.eduservice.entity.EduSubject;
import com.github.eduservice.entity.excel.ExcelSubjectData;
import com.github.eduservice.service.EduSubjectService;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
//@Component
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    /**
     * 因为这个类不能交给spring管理，所以这里也不能自动注入，这里选择用构造器注入
     */
//    @Autowired
    private EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    /**
     * 添加课程分类信息
     */
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData == null) {
            throw new NullPointerException("文件数据为空");
        }

        // 判断一级分类是否存在，如果不存在就添加
        EduSubject eduSubject = existsFirstSubject(excelSubjectData.getFirstSubjectName());
        if (eduSubject == null) {
            eduSubject = new EduSubject();
            eduSubject.setParentId(0L);
            eduSubject.setTitle(excelSubjectData.getFirstSubjectName());
            eduSubjectService.save(eduSubject);
        }

        // 判断二级分类是否存在，如果不存在就添加
        Long id = eduSubject.getId();
        eduSubject = existsSecondSubject(excelSubjectData.getSecondSubjectName(), id);
        if (eduSubject == null) {
            eduSubject = new EduSubject();
            eduSubject.setParentId(id);
            eduSubject.setTitle(excelSubjectData.getSecondSubjectName());
            eduSubjectService.save(eduSubject);
        }

    }

    /**
     * 判断一级分类是否存在
     */
    public EduSubject existsFirstSubject(String name) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "title", "parent_id").eq("title", name);
        return eduSubjectService.getOne(queryWrapper);
    }

    /**
     * 判断二级分类是否存在
     */
    public EduSubject existsSecondSubject(String name, Long parentId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name)
                .eq("parent_id", parentId)
                .select("id", "title", "parent_id");
        return eduSubjectService.getOne(queryWrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

package com.github.eduservice.service;

import com.github.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.eduservice.entity.subject.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author HAN
 * @since 2021-04-08
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 添加课程分类
     * @param file excel文件
     */
    void saveSubject(MultipartFile file);

    /**
     * 获取分类后的list
     * @return 递归分类完的list
     */
    List<Subject> getRecursionList();
}

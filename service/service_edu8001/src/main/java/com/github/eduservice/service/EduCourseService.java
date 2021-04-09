package com.github.eduservice.service;

import com.github.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.eduservice.vo.CourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程基本信息
     * @param courseInfo 前端返回的课程信息对象
     */
    void saveCourseInfo(CourseInfo courseInfo);

    /**
     * 获取课程基本信息
     * @param courseId 课程id
     * @return 返回封装了课程基本信息以及课程简介的对象
     */
    CourseInfo getCourseInfo(Long courseId);

    /**
     * 修改课程信息
     * @param courseInfo 课程信息对象
     */
    void updateCourseInfo(CourseInfo courseInfo);
}

package com.github.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.eduservice.entity.EduCourse;
import com.github.eduservice.vo.CourseFrontInfo;
import com.github.eduservice.vo.CourseInfo;
import com.github.eduservice.vo.CourseWebInfo;
import com.github.eduservice.vo.PublishInfo;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取发布时信息
     * @param courseId 课程id
     * @return 所需的发布时信息对象
     */
    PublishInfo getPublishInfo(Long courseId);

    /**
     * 删除课程数据，包括章节和小节
     * @param courseId 课程id
     */
    void deleteCourseInfo(Long courseId);

    /**
     * 根据教师id查询课程
     * @param id 教师id
     * @return 课程信息
     */
    List<EduCourse> getByTeacherId(Long id);

    /**
     * 根据条件查询分页课程
     * @param page 分页信息
     * @param courseFrontInfo 条件
     */
    Map<String, Object> getPageCondition(Page<EduCourse> page, CourseFrontInfo courseFrontInfo);

    /**
     * 查询课程以及其他详细信息
     * @param courseId 课程id
     */
    CourseWebInfo getBaseCourseInfo(Long courseId);

    /**
     * 增加浏览数量
     * @param courseId 课程id
     */
    void viewCount(Long courseId);

    /**
     * 增加购买数量
     * @param courseId 课程id
     */
    void buyCount(Long courseId);
}

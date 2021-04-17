package com.github.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.eduservice.entity.EduCourse;
import com.github.eduservice.vo.CourseWebInfo;
import com.github.eduservice.vo.PublishInfo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    PublishInfo getPublishInfo(Long id);

    CourseWebInfo getBaseCourseInfo(Long courseId);
}

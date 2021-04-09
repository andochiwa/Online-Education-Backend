package com.github.eduservice.service.impl;

import com.github.eduservice.entity.EduCourse;
import com.github.eduservice.entity.EduCourseDescription;
import com.github.eduservice.mapper.EduCourseMapper;
import com.github.eduservice.service.EduCourseDescriptionService;
import com.github.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.eduservice.vo.CourseInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public void saveCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        boolean save = super.save(eduCourse);
        if (!save) {
            // 添加失败
            throw new RuntimeException("添加课程信息失败");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        save = eduCourseDescriptionService.save(eduCourseDescription);

        if (!save) {
            throw new RuntimeException("添加课程简介失败");
        }
        // 把id添加到courseInfo中，返回给前端
        courseInfo.setId(eduCourse.getId());
    }

    @Override
    public CourseInfo getCourseInfo(Long courseId) {
        // 查询课程表
        EduCourse eduCourse = super.getById(courseId);

        // 查询课程简介表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);

        // 复制进所需对象中
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(eduCourse, courseInfo);
        BeanUtils.copyProperties(eduCourseDescription, courseInfo);


        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        // 修改课程信息表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        super.updateById(eduCourse);

        // 修改课程简介表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo, eduCourseDescription);
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }
}

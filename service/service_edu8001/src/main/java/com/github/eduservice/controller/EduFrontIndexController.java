package com.github.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.eduservice.entity.EduCourse;
import com.github.eduservice.entity.EduTeacher;
import com.github.eduservice.service.EduCourseService;
import com.github.eduservice.service.EduTeacherService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/13
 */
@RestController
@RequestMapping("/eduservice/index")
@Api("前台页面查询功能")
//@CrossOrigin
public class EduFrontIndexController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询前8条热门课程，前4个热门教师
     */
    @GetMapping
    @ApiOperation("查询前8条热门课程，前4个热门教师")
    @Cacheable(value = "banner", key = "'EduCourseTeacher'")
    public ResultCommon getTeacherCourse() {
        // 查询前8条热门课程
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        wrapperCourse.orderByDesc("view_count")
                .last("limit 8");
        List<EduCourse> eduCourses = eduCourseService.list(wrapperCourse);

        // 查询前4个热门教师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("sort")
                .last("limit 4");
        List<EduTeacher> eduTeachers = eduTeacherService.list(wrapperTeacher);

        return ResultCommon.success().setData("courses", eduCourses).setData("teachers", eduTeachers);
    }
}

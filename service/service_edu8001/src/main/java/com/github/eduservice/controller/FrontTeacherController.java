package com.github.eduservice.controller;

import com.github.eduservice.entity.EduCourse;
import com.github.eduservice.entity.EduTeacher;
import com.github.eduservice.service.EduCourseService;
import com.github.eduservice.service.EduTeacherService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 04-17-15:59
 */
@RestController
//@CrossOrigin
@RequestMapping("/eduservice/front-teacher")
@Api("查询前台教师内容")
public class FrontTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 根据id查询教师和教师负责的课程
     *
     * @param id 教师id
     */
    @GetMapping("{id}")
    @ApiOperation("查询教师信息以及负责的课程信息")
    public ResultCommon getTeacherCourse(@PathVariable("id") Long id) {

        // 查询教师信息
        EduTeacher eduTeacher = eduTeacherService.getById(id);

        // 查询课程信息
        List<EduCourse> eduCourses = eduCourseService.getByTeacherId(id);

        return ResultCommon.success().setData("teacher", eduTeacher).setData("courses", eduCourses);
    }
}

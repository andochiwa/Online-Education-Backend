package com.github.eduservice.controller;


import com.github.eduservice.service.EduCourseService;
import com.github.eduservice.vo.CourseInfo;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/eduservice/course")
@Api("课程基本信息")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 添加课程基本信息
     * @param courseInfo 前端返回的课程信息对象
     */
    @PostMapping
    public ResultCommon saveCourseInfo(@RequestBody CourseInfo courseInfo) {

        eduCourseService.saveCourseInfo(courseInfo);

        return ResultCommon.success().setData("id", courseInfo.getId());
    }

}


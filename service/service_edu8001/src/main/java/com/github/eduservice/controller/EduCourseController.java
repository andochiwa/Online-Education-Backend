package com.github.eduservice.controller;


import com.github.eduservice.service.EduCourseService;
import com.github.eduservice.vo.CourseInfo;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("添加课程基本信息")
    public ResultCommon saveCourseInfo(@RequestBody CourseInfo courseInfo) {
        eduCourseService.saveCourseInfo(courseInfo);

        return ResultCommon.success().setData("id", courseInfo.getId());
    }

    /**
     * 根据id查询课程信息
     * @param courseId 课程id
     */
    @GetMapping("{id}")
    @ApiOperation("根据id查询课程信息")
    public ResultCommon getCourseInfo(@PathVariable("id") Long courseId) {
        CourseInfo courseInfo = eduCourseService.getCourseInfo(courseId);

        return ResultCommon.success().setData("items", courseInfo);
    }

    /**
     * 修改课程信息
     * @param courseInfo 课程信息对象
     * @return
     */
    @PutMapping
    @ApiOperation("修改课程信息")
    public ResultCommon updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        eduCourseService.updateCourseInfo(courseInfo);

        return ResultCommon.success().setData("items", courseInfo);
    }

}


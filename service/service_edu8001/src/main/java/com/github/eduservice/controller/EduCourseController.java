package com.github.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.eduservice.entity.EduCourse;
import com.github.eduservice.service.EduCourseService;
import com.github.eduservice.vo.CourseInfo;
import com.github.eduservice.vo.CourseQuery;
import com.github.eduservice.vo.PublishInfo;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        return ResultCommon.success().setData("id", courseInfo.getId().toString());
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
     */
    @PutMapping
    @ApiOperation("修改课程信息")
    public ResultCommon updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        eduCourseService.updateCourseInfo(courseInfo);

        return ResultCommon.success().setData("items", courseInfo);
    }

    /**
     * 查询发布时回显信息
     * @param courseId 课程id
     */
    @GetMapping("publish/{id}")
    @ApiOperation("查询发布时回显信息")
    public ResultCommon getPublishInfo(@PathVariable("id") Long courseId) {
        PublishInfo publishInfo = eduCourseService.getPublishInfo(courseId);
        return ResultCommon.success().setData("items", publishInfo);
    }

    /**
     * 发布课程
     * @param courseId 课程id
     */
    @PutMapping("publish/{id}")
    @ApiOperation("发布课程，根据id修改status")
    public ResultCommon updatePublishInfo(@PathVariable("id") Long courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return ResultCommon.success();
    }

    /**
     * 条件查询分页数据
     * @param current 第几条数据开始
     * @param limit 要几条数据
     * @param courseQuery 条件对象
     */
    @PostMapping("condition/{current}/{limit}")
    @ApiOperation("条件查询分页数据")
    public ResultCommon getConditionCourseInfo(@PathVariable("current") long current,
                                               @PathVariable("limit") long limit,
                                               @RequestBody CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.select("id", "title", "price", "lesson_num", "view_count", "status", "gmt_create");

        // 条件查询
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if (!ObjectUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!ObjectUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }

        Page<EduCourse> coursePage = new Page<>(current, limit);

        eduCourseService.page(coursePage, wrapper);

        long total = coursePage.getTotal();
        List<EduCourse> courses = coursePage.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", courses);

        return ResultCommon.success().setData(map);
    }

    /**
     * 删除课程数据，包括章节和小节
     *
     * @param courseId 课程id
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除课程数据，包括章节和小节")
    public ResultCommon deleteCourseInfo(@PathVariable("id") Long courseId) {
        eduCourseService.deleteCourseInfo(courseId);

        return ResultCommon.success();
    }

}


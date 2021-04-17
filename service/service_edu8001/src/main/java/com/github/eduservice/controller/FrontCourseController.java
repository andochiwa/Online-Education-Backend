package com.github.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.eduservice.entity.EduCourse;
import com.github.eduservice.service.EduCourseService;
import com.github.eduservice.vo.CourseFrontInfo;
import com.github.eduservice.vo.CourseWebInfo;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author HAN
 * @version 1.0
 * @create 04-17-16:50
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/front-course")
@Api("前台课程")
public class FrontCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 条件查询带分页课程
     * @param current 第几页
     * @param limit 要几条
     * @param courseFrontInfo 条件对象
     */
    @PostMapping("condition/{current}/{limit}")
    @ApiOperation("条件查询带分页课程")
    public ResultCommon getCoursePageCondition(@PathVariable("current") long current,
                                               @PathVariable("limit") long limit,
                                               @RequestBody CourseFrontInfo courseFrontInfo) {
        Page<EduCourse> page = new Page<>(current, limit);
        // 查询
        Map<String, Object> map = eduCourseService.getPageCondition(page, courseFrontInfo);

        return ResultCommon.success().setData(map);
    }

    /**
     * 查询课程以及其他详细信息
     * @param courseId 课程id
     */
    @GetMapping("{id}")
    @ApiOperation("查询课程详细信息")
    public ResultCommon getCourseInfo(@PathVariable("id") Long courseId) {

        CourseWebInfo courseWebInfo = eduCourseService.getBaseCourseInfo(courseId);

        return ResultCommon.success().setData("items", courseWebInfo);
    }
}

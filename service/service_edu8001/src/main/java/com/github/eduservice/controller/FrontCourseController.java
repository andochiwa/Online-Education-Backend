package com.github.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.eduservice.entity.EduCourse;
import com.github.eduservice.service.EduCourseService;
import com.github.eduservice.vo.CourseFrontInfo;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
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
    public ResultCommon getCoursePageCondition(@PathVariable("current") long current,
                                               @PathVariable("limit") long limit,
                                               @RequestBody CourseFrontInfo courseFrontInfo) {
        Page<EduCourse> page = new Page<>(current, limit);
        // 查询
        Map<String, Object> map = eduCourseService.getPageCondition(page, courseFrontInfo);

        return ResultCommon.success().setData(map);
    }
}

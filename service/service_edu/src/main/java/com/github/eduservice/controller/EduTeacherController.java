package com.github.eduservice.controller;


import com.github.eduservice.entity.EduTeacher;
import com.github.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-04
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(value = "教师管理")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    // 查询所有数据
    @GetMapping("")
    @ApiOperation(value = "获取所有教师信息")
    public List<EduTeacher> getAll() {
        return eduTeacherService.list();
    }

    // 删除数据
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID逻辑删除教师")
    public void removeById(@PathVariable("id") @ApiParam(value = "教师id", name = "id", required = true)
                                       Long id) {
        eduTeacherService.removeById(id);
    }
}


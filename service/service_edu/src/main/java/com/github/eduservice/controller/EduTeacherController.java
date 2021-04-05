package com.github.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.eduservice.entity.EduTeacher;
import com.github.eduservice.service.EduTeacherService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResultCommon getAll() {
        List<EduTeacher> list = eduTeacherService.list();
        return ResultCommon.success().setData("items", list).setMessage("获取所有教师数据成功");
    }

    // 删除数据
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID逻辑删除教师")
    public ResultCommon removeById(@PathVariable("id") @ApiParam(value = "教师id", name = "id", required = true)
                                       Long id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return ResultCommon.success();
        }
        return ResultCommon.fail();
    }

    // 分页查询教师数据
    @GetMapping("/{current}/{limit}")
    @ApiOperation(value = "分页查询教师数据")
    public ResultCommon getPage(@PathVariable("current") long current,
                                @PathVariable("limit") long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", records);

        return ResultCommon.success().setData(map);
    }
}


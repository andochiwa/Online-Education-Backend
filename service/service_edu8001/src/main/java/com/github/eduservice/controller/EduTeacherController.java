package com.github.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.eduservice.entity.EduTeacher;
import com.github.eduservice.service.EduTeacherService;
import com.github.eduservice.vo.TeacherQuery;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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
//@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询所有教师数据
     */
    @GetMapping("")
    @ApiOperation(value = "获取所有教师信息")
    public ResultCommon getAll() {
        List<EduTeacher> list = eduTeacherService.list();
        return ResultCommon.success().setData("items", list).setMessage("获取所有教师数据成功");
    }

    /**
     * 根据id查询教师
     * @param id 数据库主键id
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取教师数据")
    public ResultCommon getById(@PathVariable("id") Long id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return ResultCommon.success().setData("items", eduTeacher);
    }

    /**
     * 根据id修改教师
     * @param eduTeacher 修改后教师的对象，需要包含id
     */
    @PutMapping("")
    @ApiOperation(value = "根据id修改教师数据")
    public ResultCommon update(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return ResultCommon.success();
        }
        return ResultCommon.fail();
    }

    /**
     * 根据id逻辑删除教师
     * @param id 教师id
     */
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

    /**
     * 分页查询教师数据
     * @param current 从第几条数据开始
     * @param limit 返回数据的数量
     */
    @GetMapping("/pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询教师数据")
    public ResultCommon getPage(@PathVariable("current") long current,
                                @PathVariable("limit") long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        // 创建page对象
        eduTeacherService.page(pageTeacher, null);

        // 获取总数
        long total = pageTeacher.getTotal();
        // 获取数据
        List<EduTeacher> records = pageTeacher.getRecords();
        // 封装到map中
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", records);

        return ResultCommon.success().setData(map);
    }

    @PostMapping(value = "/pageTeacherCondition/{current}/{limit}")
    @ApiOperation(value = "根据条件分页查询教师数据")
    public ResultCommon getPageCondition(@PathVariable("current") long current,
                                         @PathVariable("limit") long limit,
                                         @RequestBody TeacherQuery teacherQuery) {
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        System.out.println(teacherQuery);

        // 构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        // 判断条件是否为空，如果不为空就拼接条件
        if (StringUtils.hasText(name)) {
            wrapper.like("name", name);
        }
        if (!ObjectUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (StringUtils.hasText(begin)) {
            wrapper.gt("gmt_create", begin);
        }
        if (StringUtils.hasText(end)) {
            wrapper.le("gmt_modified", end);
        }

        // 调用方法实现分页
        eduTeacherService.page(pageTeacher, wrapper);

        // 获取总数
        long total = pageTeacher.getTotal();
        // 获取数据
        List<EduTeacher> records = pageTeacher.getRecords();
        // 封装到map中
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", records);

        return ResultCommon.success().setData(map);
    }

    /**
     * 添加教师
     * @param eduTeacher 教师信息
     */
    @PostMapping("")
    @ApiOperation("添加教师")
    public ResultCommon insert(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return ResultCommon.success();
        }
        return ResultCommon.fail();
    }
}


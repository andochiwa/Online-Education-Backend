package com.github.eduservice.controller;


import com.github.eduservice.entity.EduTeacher;
import com.github.eduservice.service.EduTeacherService;
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
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    // 查询所有数据
    @GetMapping("")
    public List<EduTeacher> getAll() {
        return eduTeacherService.list();
    }

    // 删除数据
    @DeleteMapping("{id}")
    public void removeById(@PathVariable("id") Long id) {

    }
}


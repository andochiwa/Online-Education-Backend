package com.github.eduservice.controller;


import com.github.eduservice.entity.subject.Subject;
import com.github.eduservice.service.EduSubjectService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/eduservice/subject")
@Api("课程分类")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 获取课程分类
     * 获取上传过来的文件，把文件内容读取出来
     * @param file excel文件
     */
    @PostMapping
    @ApiOperation("从excel文件中获取课程分类")
    public ResultCommon saveSubject(@RequestPart("file") MultipartFile file) {

        eduSubjectService.saveSubject(file);

        return ResultCommon.success();
    }

    /**
     * 课程分类的树形结构
     */
    @GetMapping
    @ApiOperation("递归获取课程分类信息")
    public ResultCommon getSubject() {

        List<Subject> list =  eduSubjectService.getRecursionList();

        return ResultCommon.success().setData("items", list);
    }

}


package com.github.eduservice.controller;


import com.github.eduservice.service.EduSubjectService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

}


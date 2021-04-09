package com.github.eduservice.controller;


import com.github.eduservice.entity.chapter.Chapter;
import com.github.eduservice.service.EduChapterService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/eduservice/chapter")
@Api("章节分类")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 获取章节分类后的List
     * @return 课程id
     */
    @GetMapping("{id}")
    @ApiOperation("获取章节分类")
    public ResultCommon getChapter(@PathVariable("id") Long courseId) {

        List<Chapter> chapter = eduChapterService.getChapter(courseId);

        return ResultCommon.success().setData("items", chapter);
    }

}


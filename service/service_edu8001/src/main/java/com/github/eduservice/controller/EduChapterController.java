package com.github.eduservice.controller;


import com.github.eduservice.entity.EduChapter;
import com.github.eduservice.entity.chapter.Chapter;
import com.github.eduservice.service.EduChapterService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("sort/{id}")
    @ApiOperation("获取章节分类")
    public ResultCommon getChapter(@PathVariable("id") Long courseId) {

        List<Chapter> chapter = eduChapterService.getChapter(courseId);

        return ResultCommon.success().setData("items", chapter);
    }

    /**
     * 添加章节
     * @param eduChapter 章节信息
     */
    @PostMapping
    @ApiOperation("添加章节")
    public ResultCommon saveChapter(@RequestBody EduChapter eduChapter) {

        eduChapterService.save(eduChapter);

        return ResultCommon.success();
    }

    /**
     * 根据章节id查询
     * @param chapterId 章节id
     */
    @GetMapping("{id}")
    @ApiOperation("根据章节id查询")
    public ResultCommon getChapterById(@PathVariable("id") Long chapterId) {

        EduChapter eduChapter = eduChapterService.getById(chapterId);

        return ResultCommon.success().setData("items", eduChapter);
    }

    /**
     * 更新章节
     * @param eduChapter 章节信息
     */
    @PutMapping
    @ApiOperation("更新章节")
    public ResultCommon updateChapterById(@RequestBody EduChapter eduChapter) {

        eduChapterService.updateById(eduChapter);

        return ResultCommon.success().setData("items", eduChapter);
    }

    /**
     * 根据id删除章节
     * @param chapterId 章节id
     */
    @DeleteMapping("{id}")
    @ApiOperation("根据id删除章节")
    public ResultCommon deleteChapterById(@PathVariable("id") Long chapterId) {
        eduChapterService.deleteById(chapterId);

        return ResultCommon.success();
    }

}


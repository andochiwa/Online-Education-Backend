package com.github.eduservice.controller;


import com.github.eduservice.entity.EduVideo;
import com.github.eduservice.service.EduVideoService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 根据id获取小节
     *
     */
    @GetMapping("{id}")
    @ApiOperation("根据id获取小节")
    public ResultCommon getVideoById(@PathVariable("id") Long id) {
        EduVideo eduVideo = eduVideoService.getById(id);

        return ResultCommon.success().setData("items", eduVideo);
    }

    /**
     * 添加小节
     *
     * @param eduVideo 小节信息
     */
    @PostMapping
    @ApiOperation("添加小节")
    public ResultCommon saveVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);

        return ResultCommon.success();
    }

    /**
     * 删除小节
     * TODO 需要完善：删除小节时同时删除里面的视频
     *
     * @param id 小节id
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除小节")
    public ResultCommon deleteVideo(@PathVariable("id") Long id) {
        eduVideoService.removeById(id);

        return ResultCommon.success();
    }

    /**
     * 更新小节
     */
    @PutMapping
    @ApiOperation("更新小节")
    public ResultCommon updateVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);

        return ResultCommon.success();
    }
}


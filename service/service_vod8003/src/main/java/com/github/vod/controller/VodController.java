package com.github.vod.controller;

import com.github.utils.ResultCommon;
import com.github.vod.service.VodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/12
 */
@RestController
@RequestMapping("/edu-vod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频
     * @param file 视频文件
     */
    @PostMapping
    @ApiOperation("上传视频")
    public ResultCommon uploadVideo(@RequestPart("file") MultipartFile file) {
        // 获取视频id
        String videoId = vodService.uploadVideo(file);

        return ResultCommon.success().setData("videoId", videoId);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除视频")
    public ResultCommon deleteVideo(@PathVariable("id") String id) {
        vodService.deleteVideo(id);
        return ResultCommon.success();
    }
}

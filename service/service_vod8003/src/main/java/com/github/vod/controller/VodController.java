package com.github.vod.controller;

import com.github.utils.ResultCommon;
import com.github.vod.service.VodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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

    // 上传视频
    @PostMapping
    @ApiOperation("上传视频")
    public ResultCommon uploadVideo(@RequestPart("file") MultipartFile file) {
        // 获取视频id
        String videoId = vodService.uploadVideo(file);

        return ResultCommon.success().setData("videoId", videoId);
    }
}

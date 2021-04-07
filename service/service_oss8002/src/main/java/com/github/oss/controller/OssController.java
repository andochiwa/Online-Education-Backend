package com.github.oss.controller;

import com.github.oss.service.OssService;
import com.github.utils.ResultCommon;
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
 * @create 2021/4/8
 */
@RestController
@RequestMapping("/eduOss/fileOss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像
     * @param file 头像对象
     */
    @PostMapping
    @ApiOperation("上传图片")
    public ResultCommon uploadOssFile(@RequestPart("file") MultipartFile file) {

        // 返回上传到oss的路径
        String url = ossService.uploadFile(file);

        return ResultCommon.success().setData("url", url);
    }
}

package com.github.vod.service;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vod.mapper.VodMapper;
import com.github.vod.properties.VodProperties;
import com.github.vod.utils.InitVodClient;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/12
 */
@Service
public class VodService extends ServiceImpl<VodMapper, VodProperties> {


    /**
     * 上传视频
     * @param file 视频文件
     * @return 视频id
     */
    @SneakyThrows
    public String uploadVideo(MultipartFile file) {

        QueryWrapper<VodProperties> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "aliyun");
        VodProperties vodProperties = super.getOne(wrapper);

        // 上传文件的名字
        String filename = file.getOriginalFilename();
        // 设置上传后的文件的名字
        String title = filename.substring(0, filename.lastIndexOf("."));
        // 获取文件流
        InputStream inputStream = file.getInputStream();

        UploadStreamRequest request = new UploadStreamRequest(vodProperties.getKeyId(),
                vodProperties.getKeySecret(),
                title,
                filename,
                inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        return response.getVideoId();
    }

    /**
     * 删除视频
     * @param id 视频id
     */
    @SneakyThrows
    public void deleteVideo(String id) {
        QueryWrapper<VodProperties> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "aliyun");
        VodProperties vodProperties = super.getOne(wrapper);

        DefaultAcsClient client = InitVodClient.initVodClient(vodProperties.getKeyId(), vodProperties.getKeySecret());

        DeleteVideoRequest request = new DeleteVideoRequest();

        request.setVideoIds(id);

        client.getAcsResponse(request);
    }
}

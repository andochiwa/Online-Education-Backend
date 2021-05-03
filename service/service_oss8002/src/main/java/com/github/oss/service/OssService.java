package com.github.oss.service;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.oss.mapper.OssMapper;
import com.github.oss.properties.ConstantProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@Service
public class OssService extends ServiceImpl<OssMapper, ConstantProperties> {
    public String uploadFile(MultipartFile file) {
        QueryWrapper<ConstantProperties> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "aliyun");
        ConstantProperties properties = super.getOne(wrapper);

        String endpoint = properties.getEndpoint();
        String accessKeyId = properties.getKeyId();
        String accessKeySecret = properties.getKeySecret();
        String bucketName = properties.getBucketName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now());
        // 设置文件名随机值, 加上日期路径，这样就能自动创建文件夹
        String fileName = date + "/" + IdUtil.simpleUUID() +
                file.getOriginalFilename();

        // 获取上传文件的输入流
        try (InputStream inputStream = file.getInputStream()) {
            ossClient.putObject(bucketName, fileName, inputStream);
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }

    }
}

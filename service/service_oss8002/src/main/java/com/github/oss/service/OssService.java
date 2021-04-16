package com.github.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.oss.mapper.OssMapper;
import com.github.oss.properties.ConstantProperties;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@Service
public class OssService extends ServiceImpl<OssMapper, ConstantProperties> {
    @SneakyThrows
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

        // 获取上传文件的输入流
        InputStream inputStream = file.getInputStream();

        // 设置文件名随机值
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") +
                file.getOriginalFilename();

        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now());
        // 加上日期路径，这样就能自动创建文件夹
        fileName = date + "/" + fileName;

        // 上传。
        ossClient.putObject(bucketName, fileName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}

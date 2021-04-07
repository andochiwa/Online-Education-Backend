package com.github.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.github.oss.properties.ConstantProperties;
import com.github.oss.service.OssService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
@Service
public class OssServiceImpl implements OssService {
    @SneakyThrows
    @Override
    public String uploadFile(MultipartFile file) {

        String endpoint = ConstantProperties.ENDPOINT;
        String accessKeyId = ConstantProperties.KEY_ID;
        String accessKeySecret = ConstantProperties.KEY_SECRET;
        String bucketName = ConstantProperties.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 获取上传文件的输入流
        InputStream inputStream = file.getInputStream();

        String fileName = file.getOriginalFilename();

        // 上传。
        ossClient.putObject(bucketName, fileName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}

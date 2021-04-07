package com.github.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/8
 */
public interface OssService {
    /**
     * 上传文件到oss
     * @param file 上传的文件对象
     * @return 上传的文件路径
     */
    String uploadFile(MultipartFile file);
}

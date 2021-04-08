package com.github.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.eduservice.entity.EduSubject;
import com.github.eduservice.entity.excel.ExcelSubjectData;
import com.github.eduservice.listener.SubjectExcelListener;
import com.github.eduservice.mapper.EduSubjectMapper;
import com.github.eduservice.service.EduSubjectService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @SneakyThrows
    @Override
    public void saveSubject(MultipartFile file) {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(this)).sheet().doRead();
    }
}

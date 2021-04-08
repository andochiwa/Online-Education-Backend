package com.github.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.eduservice.entity.EduSubject;
import com.github.eduservice.entity.excel.ExcelSubjectData;
import com.github.eduservice.entity.subject.Subject;
import com.github.eduservice.listener.SubjectExcelListener;
import com.github.eduservice.mapper.EduSubjectMapper;
import com.github.eduservice.service.EduSubjectService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Subject> getRecursionList() {
        // 获取所有数据
        List<EduSubject> eduSubjects = super.list();

        // 使用流进行递归查询
        List<Subject> result = eduSubjects.stream()
                .filter(item -> item.getParentId() == 0) // 过滤出父节点
                .map(item ->  new Subject(item.getId(),
                        item.getTitle(), null))// 转换成Subject对象
                .peek(item -> {
                    item.setChildren(getChildrenList(item, eduSubjects));
                })
                .collect(Collectors.toList());

        return result;
    }

    /**
     * 递归查询子节点
     * @return 查询后的子节点
     */
    public List<Subject> getChildrenList(Subject root, List<EduSubject> subjects) {
        List<Subject> result = subjects.stream()
                .filter(item -> item.getParentId().equals(root.getId())) // 找出与父节点相同的节点
                .map(item ->  new Subject(item.getId(),
                        item.getTitle(), null)) // 转换成Subject对象
                .peek(item -> {
                    item.setChildren(getChildrenList(item, subjects));
                })
                .collect(Collectors.toList());
        return result;
    }
}

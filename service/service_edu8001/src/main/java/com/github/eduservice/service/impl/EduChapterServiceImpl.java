package com.github.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.eduservice.entity.EduChapter;
import com.github.eduservice.entity.EduVideo;
import com.github.eduservice.entity.chapter.Chapter;
import com.github.eduservice.mapper.EduChapterMapper;
import com.github.eduservice.service.EduChapterService;
import com.github.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
@Service
@Transactional
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<Chapter> getChapter(Long courseId) {
        // 获取所有章节数据
        List<EduChapter> chapters = super.list();
        // 获取所有小节数据
        List<EduVideo> videos = eduVideoService.list();

        // 递归查询
        return chapters.stream()
                .filter(item -> Objects.equals(item.getCourseId(), courseId)) // 过滤出所需课程
                .map(item -> new Chapter(item.getId(), item.getTitle(), null)) // 转换为所需对象
                .peek(item -> item.setChildren(getChildren(item, videos)))
                .collect(Collectors.toList());
    }

    public List<Chapter> getChildren(Chapter root, List<EduVideo> videos) {
        return videos.stream()
                .filter(item -> Objects.equals(item.getChapterId(), root.getId())) // 把小节中的章节id对应章节的过滤出来
                .map(item -> new Chapter(item.getId(), item.getTitle(), null))
                .peek(item -> item.setChildren(getChildren(item, videos)))
                .collect(Collectors.toList());
    }
}

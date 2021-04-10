package com.github.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.eduservice.entity.EduChapter;
import com.github.eduservice.entity.chapter.Chapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 获取章节分类后的List
     * @param courseId 课程id
     */
    List<Chapter> getChapter(Long courseId);

    /**
     * 如果有小节则先删除小节
     * @param id 章节id
     */
    void deleteById(Long chapterId);
}

package com.github.eduservice.service;

import com.github.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 删除小节以及视频
     * @param id 小节id
     */
    void removeVideo(Long id);

    /**
     * 删除小节以及视频
     * @param chapterId 章节id
     */
    void removeByChapterId(Long chapterId);
}

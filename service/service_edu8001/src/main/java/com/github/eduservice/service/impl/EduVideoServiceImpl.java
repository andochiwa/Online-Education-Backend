package com.github.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.eduservice.entity.EduVideo;
import com.github.eduservice.feign.VodClient;
import com.github.eduservice.mapper.EduVideoMapper;
import com.github.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
@Service
@Transactional
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    @GlobalTransactional
    public void removeVideo(Long id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.select("video_source_id").eq("id", id);
        EduVideo eduVideo = super.getOne(wrapper);
        // 删除视频
        vodClient.deleteVideo(eduVideo.getVideoSourceId());
        // 删除小节
        super.removeById(id);
    }

    @Override
    @GlobalTransactional
    public void removeByChapterId(Long chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId).select("video_source_id");
        List<EduVideo> eduVideos = super.list(wrapper);
        // 删除视频
        eduVideos.forEach(eduVideo -> vodClient.deleteVideo(eduVideo.getVideoSourceId()));
        // 删除小节
        super.remove(wrapper);
    }
}

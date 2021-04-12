package com.github.eduservice.service.impl;

import com.github.eduservice.entity.EduVideo;
import com.github.eduservice.feign.VodClient;
import com.github.eduservice.mapper.EduVideoMapper;
import com.github.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-09
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideo(Long id) {
        EduVideo eduVideo = super.getById(id);
        // 删除视频
        vodClient.deleteVideo(eduVideo.getVideoSourceId());
        // 删除小节
        super.removeById(id);
    }
}

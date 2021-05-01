package com.github.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.eduservice.entity.EduComment;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author HAN
 * @since 2021-05-01
 */
public interface EduCommentService extends IService<EduComment> {

    /**
     * 获取评论信息
     * @param courseId 课程id
     * @return 封装好的评论信息
     */
    List<EduComment> getComment(Long courseId);

}

package com.github.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.eduservice.entity.EduComment;
import com.github.eduservice.mapper.EduCommentMapper;
import com.github.eduservice.service.EduCommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-05-01
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public List<EduComment> getComment(Long courseId) {
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduComment> comments = super.list(wrapper);
        return comments.stream()
                .filter(item -> item.getParentId() == 0)
                .peek(item -> item.setChildren(commentRecursiveHelper(item, comments)))
                .collect(Collectors.toList());
    }

    private List<EduComment> commentRecursiveHelper(EduComment comment, List<EduComment> comments) {
        return comments.stream()
                .filter(item -> Objects.equals(item.getParentId(), comment.getId()))
                .peek(item -> item.setChildren(commentRecursiveHelper(item, comments)))
                .collect(Collectors.toList());
    }
}

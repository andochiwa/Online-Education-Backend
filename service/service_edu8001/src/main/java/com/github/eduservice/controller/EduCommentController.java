package com.github.eduservice.controller;


import com.github.eduservice.entity.EduComment;
import com.github.eduservice.service.EduCommentService;
import com.github.utils.JwtUtils;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-05-01
 */
@RestController
@RequestMapping("/eduservice/edu-comment")
@Api(tags = "评论功能")
public class EduCommentController {

    @Autowired
    private EduCommentService eduCommentService;

    /**
     * 获取评论信息
     * @param courseId 课程id
     */
    @GetMapping("{courseId}")
    @ApiOperation("获取评论")
    public ResultCommon getComment(@PathVariable("courseId") Long courseId) {
        List<EduComment> commentList = eduCommentService.getComment(courseId);
        return ResultCommon.success().setData("items", commentList);
    }

    /**
     * 添加一级评论信息
     */
    @PostMapping("{courseId}}")
    @ApiOperation("添加一级评论信息")
    public ResultCommon saveCommentLevelOne(@RequestBody EduComment comment, HttpServletRequest request) {
        String userId = JwtUtils.getUserIdByJwtToken(request);
        if (ObjectUtils.isEmpty(userId)) {
            return ResultCommon.fail().setMessage("请先登录");
        }

        return ResultCommon.success();
    }


}


package com.github.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.eduservice.entity.*;
import com.github.eduservice.feign.VodClient;
import com.github.eduservice.mapper.EduCourseMapper;
import com.github.eduservice.service.*;
import com.github.eduservice.vo.CourseFrontInfo;
import com.github.eduservice.vo.CourseInfo;
import com.github.eduservice.vo.CourseWebInfo;
import com.github.eduservice.vo.PublishInfo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Qualifier("com.github.eduservice.feign.VodClient")
    @Autowired
    private VodClient vodClient;

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Override
    public void saveCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        boolean save = super.save(eduCourse);
        if (!save) {
            // 添加失败
            throw new RuntimeException("添加课程信息失败");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        save = eduCourseDescriptionService.save(eduCourseDescription);

        if (!save) {
            throw new RuntimeException("添加课程简介失败");
        }
        // 把id添加到courseInfo中，返回给前端
        courseInfo.setId(eduCourse.getId());
    }

    @Override
    public CourseInfo getCourseInfo(Long courseId) {
        // 查询课程表
        EduCourse eduCourse = super.getById(courseId);

        // 查询课程简介表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);

        // 复制进所需对象中
        CourseInfo courseInfo = new CourseInfo();
        if (eduCourse != null) {
            BeanUtils.copyProperties(eduCourse, courseInfo);
        }
        if (eduCourseDescription != null) {
            BeanUtils.copyProperties(eduCourseDescription, courseInfo);
        }


        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        // 修改课程信息表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        super.updateById(eduCourse);

        // 修改课程简介表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo, eduCourseDescription);
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public PublishInfo getPublishInfo(Long courseId) {
        return baseMapper.getPublishInfo(courseId);
    }

    @Override
    @GlobalTransactional
    public void deleteCourseInfo(Long courseId) {
        // 删除课程和课程简介
        super.removeById(courseId);
        eduCourseDescriptionService.removeById(courseId);

        // 为了删除视频，先获取视频的id
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.select("video_source_id").eq("course_id", courseId);
        List<EduVideo> eduVideos = eduVideoService.list(videoQueryWrapper);
        eduVideos.forEach(item -> vodClient.deleteVideo(item.getVideoSourceId()));

        // 删除章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        eduChapterService.remove(chapterQueryWrapper);

        // 删除小节
        videoQueryWrapper.clear();
        videoQueryWrapper.eq("course_id", courseId);
        eduVideoService.remove(videoQueryWrapper);
    }

    @Override
    public List<EduCourse> getByTeacherId(Long id) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", id);
        return super.list(wrapper);
    }

    @Override
    public Map<String, Object> getPageCondition(Page<EduCourse> page, CourseFrontInfo courseFrontInfo) {
        // 设置条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        // 判断一级分类
        if (!ObjectUtils.isEmpty(courseFrontInfo.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseFrontInfo.getSubjectParentId());
            // 判断二级分类
            if (!ObjectUtils.isEmpty(courseFrontInfo.getSubjectId())) {
                wrapper.eq("subject_id", courseFrontInfo.getSubjectId());
            }
        }

        // 判断销量
        if (!ObjectUtils.isEmpty(courseFrontInfo.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }
        // 判断价格
        if (!ObjectUtils.isEmpty(courseFrontInfo.getPriceSort())) {
            wrapper.orderByDesc("price");
        }
        // 判断创建时间
        if (!ObjectUtils.isEmpty(courseFrontInfo.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }

        if (!ObjectUtils.isEmpty(courseFrontInfo.getCourseName())) {
            wrapper.like("title", courseFrontInfo.getCourseName());
        }

        Page<EduCourse> coursePage = super.page(page, wrapper);

        // 存储list集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", coursePage.getRecords());
        map.put("total", coursePage.getTotal());

        return map;
    }

    @Override
    public CourseWebInfo getBaseCourseInfo(Long courseId) {
        return eduCourseMapper.getBaseCourseInfo(courseId);
    }

    @Override
    public void viewCount(Long CourseId) {
        super.baseMapper.viewCount(CourseId);
    }

    @Override
    public void buyCount(Long courseId) {
        super.baseMapper.buyCount(courseId);
    }


}

package com.github.eduservice.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/11
 */
@Data
@Api("发布时课程信息")
public class PublishInfo {

    @ApiModelProperty("课程id")
    private Long id;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty("一级课程名")
    private String subjectFirst;

    @ApiModelProperty("二级课程名")
    private String subjectSecond;

    @ApiModelProperty("课程教师")
    private String teacherName;

    @ApiModelProperty("课程费用，只用作显示")
    private String price;

}

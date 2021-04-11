package com.github.eduservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/12
 */
@Data
public class CourseQuery {

    @ApiModelProperty("课程名称")
    private String title;

    @ApiModelProperty("发布状态 Draft: 未发布  Normal: 已发布")
    private String status;

}

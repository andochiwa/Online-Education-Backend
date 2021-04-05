package com.github.eduservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/5
 */
@Data
public class EduTeacherQuery {

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "教师头衔，1：高级， 2：首席")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2021-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2021-12-31 10:10:10")
    private String end;
}

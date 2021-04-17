package com.github.eduservice.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HAN
 * @version 1.0
 * @create 04-17-16:46
 */
@Data
public class CourseFrontInfo {
    @ApiModelProperty(value = "课程专业ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectParentId;

    @ApiModelProperty("销量排序")
    private String buyCountSort;

    @ApiModelProperty("创建时间排序")
    private String gmtCreateSort;

    @ApiModelProperty("价格排序")
    private String priceSort;
}

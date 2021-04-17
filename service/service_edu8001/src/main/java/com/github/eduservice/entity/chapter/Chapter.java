package com.github.eduservice.entity.chapter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private String videoSourceId;

    private List<Chapter> children;

}

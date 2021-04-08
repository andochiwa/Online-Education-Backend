package com.github.eduservice.entity.subject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/9
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subject {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private List<Subject> children;

}

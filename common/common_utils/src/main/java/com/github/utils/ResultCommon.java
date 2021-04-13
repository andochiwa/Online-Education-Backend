package com.github.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/5
 */
@Data
@Accessors(chain = true)
public class ResultCommon implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static int SUCCESS = 200;
    // 表示操作失败
    public static int ERROR = 404;

    @ApiModelProperty("返回码")
    private int code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<>();

    private ResultCommon(){}

    public static ResultCommon success() {
        ResultCommon resultCommon = new ResultCommon();

        resultCommon.setCode(SUCCESS);
        resultCommon.setMessage("成功");
        return resultCommon;
    }

    public static ResultCommon fail() {
        ResultCommon resultCommon = new ResultCommon();

        resultCommon.setCode(ERROR);
        resultCommon.setMessage("失败");
        return resultCommon;
    }

    public ResultCommon setData(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }
}

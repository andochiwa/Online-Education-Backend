package com.github.servicebase.exception;

import com.github.utils.ResultCommon;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/5
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理全局异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultCommon error(Exception e) {
        e.printStackTrace();
        return ResultCommon.fail().setMessage("全局异常处理");
    }

}

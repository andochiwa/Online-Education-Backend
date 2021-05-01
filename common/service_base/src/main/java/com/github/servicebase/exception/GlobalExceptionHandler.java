package com.github.servicebase.exception;

import com.github.utils.ResultCommon;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.util.ObjectUtils;
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
        try {
            // 回滚分布式事务
            if (!ObjectUtils.isEmpty(RootContext.getXID())) {
                GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            }
        } catch (TransactionException transactionException) {
            transactionException.printStackTrace();
        }
        return ResultCommon.fail().setMessage("发生了错误");
    }

}

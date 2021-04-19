package com.github.order.controller;


import com.github.order.service.PayLogService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-19
 */
@RestController
@RequestMapping("/order/pay-log")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    /**
     * 更新订单支付状态
     * @param id 订单id
     */
    @PutMapping("{orderId}")
    @ApiOperation("更新订单支付状态")
    public ResultCommon getOrderStatus(@PathVariable("orderId") String id) {
        payLogService.savePayLog(id);
        return ResultCommon.success();
    }

}


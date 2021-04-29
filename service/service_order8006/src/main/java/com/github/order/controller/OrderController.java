package com.github.order.controller;


import com.github.order.entity.Order;
import com.github.order.service.OrderService;
import com.github.utils.JwtUtils;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-19
 */
@RestController
@RequestMapping("/order/order")
//@CrossOrigin
@Api("订单信息")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 生成订单
     * @param courseId 课程id
     * @param request 原生request对象
     */
    @PostMapping("{courseId}")
    @ApiOperation("生成订单")
    public ResultCommon saveOrder(@PathVariable("courseId") long courseId,
                                  HttpServletRequest request) {

        // 生成订单号
        String orderId = orderService.saveOrder(courseId, JwtUtils.getUserIdByJwtToken(request));

        return ResultCommon.success().setData("orderId", orderId);
    }

    /**
     * 查询订单
     * @param id 订单号
     */
    @GetMapping("{orderId}")
    @ApiOperation("订单id查询订单")
    public ResultCommon getOrder(@PathVariable("orderId") String id) {
        Order order = orderService.getOrder(id);
        return ResultCommon.success().setData("items", order);
    }

    /**
     * 查询订单是否存在
     * @param courseId 课程id
     */
    @GetMapping("course-status/{courseId}")
    @ApiOperation("课程和用户id查询订单是否存在")
    public ResultCommon isBuyCourse(@PathVariable("courseId") Long courseId,
                                   HttpServletRequest request) {
        int count = orderService.isBuyCourse(courseId, JwtUtils.getUserIdByJwtToken(request));
        return count <= 0 ? ResultCommon.fail() : ResultCommon.success();
    }

}


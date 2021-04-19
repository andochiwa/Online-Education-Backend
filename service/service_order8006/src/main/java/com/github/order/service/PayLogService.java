package com.github.order.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.order.entity.Order;
import com.github.order.entity.PayLog;
import com.github.order.mapper.PayLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-19
 */
@Service
@Transactional
public class PayLogService extends ServiceImpl<PayLogMapper, PayLog> {

    @Autowired
    private OrderService orderService;

    /**
     * 更新订单支付状态，并插入订单支付记录
     * @param id 订单id
     */
    public void savePayLog(String id) {
        // 获取订单数据
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", id);
        Order order = orderService.getOne(orderQueryWrapper);
        // 更新订单状态
        order.setStatus(1);
        orderService.updateById(order);

        // 插入记录
        PayLog payLog = new PayLog();
        payLog.setOrderNo(id);
        payLog.setTotalFee(order.getTotalFee());
        payLog.setTransactionId(RandomUtil.randomNumbers(11));
        payLog.setPayTime(LocalDateTime.now());
        super.save(payLog);
    }
}

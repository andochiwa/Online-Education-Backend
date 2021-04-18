package com.github.order.service.impl;

import com.github.order.entity.PayLog;
import com.github.order.mapper.PayLogMapper;
import com.github.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-19
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}

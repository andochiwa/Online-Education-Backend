package com.github.order.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.order.entity.Order;
import com.github.order.feign.EduFeign;
import com.github.order.feign.UcenterFeign;
import com.github.order.mapper.OrderMapper;
import com.github.servicebase.entity.CourseWebInfoCommon;
import com.github.servicebase.entity.UcenterMemberCommon;
import com.github.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-19
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    private EduFeign eduFeign;

    @Autowired
    private UcenterFeign ucenterFeign;

    /**
     * 返回订单号
     * @param courseId 课程id
     * @param userId 用户id
     * @return 订单号
     */
    public String saveOrder(long courseId, String userId) {
        // 获取用户信息
        ResultCommon userResult = ucenterFeign.infoUserById(userId);
        UcenterMemberCommon user = (UcenterMemberCommon) userResult.getData().get("items");

        // 获取课程信息
        ResultCommon eduResult = eduFeign.getCourseInfoCommon(courseId);
        CourseWebInfoCommon course = (CourseWebInfoCommon) eduResult.getData().get("items");

        // 创建订单对象
        Order order = new Order();
        order.setOrderNo(IdUtil.simpleUUID());
        order.setCourseId(courseId);
        order.setCourseTitle(course.getTitle());
        order.setCourseCover(course.getCover());
        order.setTeacherName(course.getTeacherName());
        order.setNickname(user.getNickname());
        order.setMemberId(userId);
        order.setEmail(user.getEmail());
        order.setTotalFee(course.getPrice());

        super.save(order);

        return order.getOrderNo();
    }
}
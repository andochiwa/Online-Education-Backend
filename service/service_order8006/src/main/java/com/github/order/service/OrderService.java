package com.github.order.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.order.entity.Order;
import com.github.order.feign.EduFeign;
import com.github.order.feign.UcenterFeign;
import com.github.order.mapper.OrderMapper;
import com.github.servicebase.entity.UcenterMemberCommon;
import com.github.utils.ResultCommon;
import io.seata.spring.annotation.GlobalTransactional;
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
    @GlobalTransactional(name = "OrderTransaction", rollbackFor = Exception.class)
    public String saveOrder(long courseId, String userId) {
        // 获取用户信息
        ResultCommon userResult = ucenterFeign.infoUserById(userId);
        // 转为json格式
        String json = JSON.toJSONString(userResult.getData().get("items"));
        // 转成对象
        UcenterMemberCommon user = JSON.parseObject(json, UcenterMemberCommon.class);


        // 获取课程信息
        ResultCommon eduResult = eduFeign.getCourseInfoCommon(courseId);
        // 转为json
//        json = JSON.toJSONString(eduResult.getData().get("items"));
//        CourseWebInfoCommon course = JSON.parseObject(json, CourseWebInfoCommon.class);

        // 创建订单对象
        Order order = new Order();
        order.setOrderNo(IdUtil.simpleUUID());
        order.setCourseId(courseId);
//        order.setCourseTitle(course.getTitle());
//        order.setCourseCover(course.getCover());
//        order.setTeacherName(course.getTeacherName());
//        order.setNickname(user.getNickname());
        order.setMemberId(userId);
//        order.setEmail(user.getEmail());
//        order.setTotalFee(course.getPrice());

        super.save(order);

        return order.getOrderNo();
    }

    /**
     * 查询订单
     * @param id 订单id
     * @return 订单对象
     */
    public Order getOrder(String id) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", id);
        return super.getOne(wrapper);
    }

    /**
     * 查询订单是否存在且支付完成
     * @param courseId 课程id
     * @param userId 用户id
     * @return 查询出来的数量
     */
    public int isBuyCourse(Long courseId, String userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId)
                .eq("member_id", userId)
                .eq("status", 1);
        return super.count(wrapper);
    }
}

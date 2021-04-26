package com.github.stat.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.servicebase.cache.MybatisRedisCacheConfig;
import com.github.stat.entity.StatisticsDaily;
import com.github.stat.feign.UcenterClient;
import com.github.stat.mapper.StatisticsDailyMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-20
 */
@Service
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public class StatisticsDailyService extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> {

    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 统计某天的信息
     * @param date 哪一天
     */
    public void countRegister(String date) {
        int count = ucenterClient.statRegister(date);

        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(count);
        daily.setDateCalculated(date);
        daily.setVideoViewNum(RandomUtil.randomInt(50));
        daily.setLoginNum(RandomUtil.randomInt(50));
        daily.setCourseNum(RandomUtil.randomInt(50));
        try {
            super.save(daily);
        } catch (Exception e) {
            QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
            wrapper.eq("date_calculated", date);
            daily.setGmtCreate(null);
            daily.setGmtModified(null);
            super.update(daily, wrapper);
        }
    }

    /**
     * 查询图表数据
     * @param type 数据类型
     * @param begin 开始日期
     * @param end 结束日期
     * @return 封装好的map集合
     */
    public Map<String, Object> showData(String type, String begin, String end) {

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(begin) && !begin.equals("undefined")) {
            wrapper.ge("date_calculated", begin);
        }
        if (!ObjectUtils.isEmpty(end) && !end.equals("undefined")) {
            wrapper.le("date_calculated", end);
        }
        wrapper.select(type, "date_calculated");
        List<StatisticsDaily> list = super.list(wrapper);

        // 找出行date数据
        List<String> dates = list.stream().map(StatisticsDaily::getDateCalculated).collect(Collectors.toList());

        // 找出列count数据
        List<Integer> counts = list.stream().map(
                item -> switch (type) {
            case "register_num" -> item.getRegisterNum();
            case "login_num" -> item.getLoginNum();
            case "video_view_num" -> item.getVideoViewNum();
            case "course_num" -> item.getCourseNum();
            default -> null;
        }).collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dates);
        map.put("countList", counts);

        return map;
    }
}

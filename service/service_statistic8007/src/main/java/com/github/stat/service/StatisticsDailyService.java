package com.github.stat.service;

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

import java.util.ArrayList;
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
    public void countStat(String date) {
        int countRegister = ucenterClient.statRegister(date);

        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(countRegister);
        daily.setDateCalculated(date);
        daily.setVideoViewNum(0);
        daily.setLoginNum(0);
        daily.setCourseNum(0);
        try {
            super.save(daily);
        } catch (Exception ignored) {
        }
    }

    /**
     * 查询图表数据
     * @param begin 开始日期
     * @param end 结束日期
     * @return 封装好的map集合
     */
    public Map<String, Object> showData(String begin, String end) {

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(begin) && !begin.equals("undefined")) {
            wrapper.ge("date_calculated", begin);
        }
        if (!ObjectUtils.isEmpty(end) && !end.equals("undefined")) {
            wrapper.le("date_calculated", end);
        }
        wrapper.orderByAsc("date_calculated");
        List<StatisticsDaily> list = super.list(wrapper);

        // 找出行date数据
        List<String> dates = list.stream().map(StatisticsDaily::getDateCalculated).collect(Collectors.toList());

        List<List<Integer>> counts = new ArrayList<>();
        counts.add(list.stream().map(StatisticsDaily::getRegisterNum).collect(Collectors.toList()));
        counts.add(list.stream().map(StatisticsDaily::getLoginNum).collect(Collectors.toList()));
        counts.add(list.stream().map(StatisticsDaily::getVideoViewNum).collect(Collectors.toList()));
        counts.add(list.stream().map(StatisticsDaily::getCourseNum).collect(Collectors.toList()));

        // 记录名字
        List<String> names = new ArrayList<>();
        names.add("每日注册人数");
        names.add("每日登陆人数");
        names.add("视频观看人数");
        names.add("课程观看人数");

        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dates);
        map.put("countList", counts);
        map.put("names", names);

        return map;
    }

    /**
     * 登录数+1
     * @param date 日期
     */
    public void loginCount(String date) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        if (super.count(wrapper) <= 0) {
            countStat(date);
        }
        super.baseMapper.loginCount(date);
    }

    /**
     * 课程观看数+1
     * @param date 日期
     */
    public void courseViewCount(String date) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        if (super.count(wrapper) <= 0) {
            countStat(date);
        }
        super.baseMapper.courseViewCount(date);
    }

    /**
     * 视频观看数+1
     * @param date 日期
     */
    public void videoViewCount(String date) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        if (super.count(wrapper) <= 0) {
            countStat(date);
        }
        super.baseMapper.videoViewCount(date);
    }
}

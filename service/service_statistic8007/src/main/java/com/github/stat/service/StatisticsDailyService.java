package com.github.stat.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.stat.entity.StatisticsDaily;
import com.github.stat.feign.UcenterClient;
import com.github.stat.mapper.StatisticsDailyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author HAN
 * @since 2021-04-20
 */
@Service
public class StatisticsDailyService extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> {

    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 统计某天的注册人数
     * @param date 哪一天
     * @return 注册人数
     */
    public Integer countRegister(String date) {
        int count = ucenterClient.statRegister(date);

        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(count);
        daily.setDateCalculated(date);
        daily.setVideoViewNum(RandomUtil.randomInt(50));
        daily.setLoginNum(RandomUtil.randomInt(50));
        daily.setCourseNum(RandomUtil.randomInt(50));
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        super.remove(wrapper);
        super.save(daily);
        return count;
    }
}

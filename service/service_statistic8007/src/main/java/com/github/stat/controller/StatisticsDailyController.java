package com.github.stat.controller;


import com.github.stat.service.StatisticsDailyService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/stat/statistics-daily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 统计某一天的注册人数
     * @param date 哪一天
     */
    @PostMapping("{date}")
    @ApiOperation("统计某一天的注册人数")
    public ResultCommon countRegister(@PathVariable("date") String date) {
        statisticsDailyService.countRegister(date);

        return ResultCommon.success();
    }

    /**
     * 查询图表显示数据
     * @param type 数据类型
     * @param begin 开始日期
     * @param end 结束日期
     */
    @GetMapping("{type}/{begin}/{end}")
    @ApiOperation("查询图表显示数据")
    public ResultCommon showData(@PathVariable("type") String type,
                                 @PathVariable("begin") String begin,
                                 @PathVariable("end") String end) {
        Map<String, Object> map = statisticsDailyService.showData(type, begin, end);

        return ResultCommon.success().setData(map);
    }
}


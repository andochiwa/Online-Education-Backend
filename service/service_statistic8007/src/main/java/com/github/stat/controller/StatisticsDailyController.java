package com.github.stat.controller;


import com.github.stat.service.StatisticsDailyService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 统计某一天的注册人数
     * @param date 哪一天
     */
    @GetMapping("{date}")
    @ApiOperation("统计某一天的注册人数")
    public ResultCommon countRegister(@PathVariable("date") String date) {
        Integer count = statisticsDailyService.countRegister(date);

        return ResultCommon.success().setData("count", count);
    }
}


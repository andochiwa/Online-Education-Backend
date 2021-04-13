package com.github.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.cms.entity.CrmBanner;
import com.github.cms.service.CrmBannerService;
import com.github.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author HAN
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/cms/banner")
@Api("crm数据管理")
@CrossOrigin
public class CrmBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    /**
     * 通过id获取数据
     * @param id 数据id
     */
    @GetMapping("{id}")
    @ApiOperation("通过id获取数据")
    public ResultCommon getCrm(@PathVariable("id") Long id) {
        CrmBanner crmBanner = crmBannerService.getById(id);

        return ResultCommon.success().setData("items", crmBanner);
    }

    /**
     * 通过id删除数据
     * @param id 数据id
     */
    @DeleteMapping("{id}")
    @ApiOperation("通过id删除数据")
    public ResultCommon deleteCrm(@PathVariable("id") Long id) {
        crmBannerService.removeById(id);

        return ResultCommon.success();
    }

    /**
     * 更新数据
     * @param crmBanner 数据信息
     */
    @PutMapping()
    @ApiOperation("更新数据")
    public ResultCommon updateCrm(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);

        return ResultCommon.success();
    }

    /**
     * 插入数据
     * @param crmBanner 数据信息
     */
    @PostMapping()
    @ApiOperation("插入数据")
    public ResultCommon saveCrm(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);

        return ResultCommon.success();
    }

    /**
     * 获取分页数据
     * @param current 从第几条数据开始
     * @param limit 要几条数据
     */
    @GetMapping("{current}/{limit}")
    @ApiOperation("获取分页数据")
    public ResultCommon getCrmPagination(@PathVariable("current") Long current,
                                         @PathVariable("limit") Long limit) {
        Page<CrmBanner> page = new Page<>(current, limit);
        Page<CrmBanner> crmBannerPage = crmBannerService.page(page);

        return ResultCommon.success()
                .setData("items", crmBannerPage.getRecords())
                .setData("total", crmBannerPage.getTotal());
    }

    /**
     * 获取后三条数据
     */
    @GetMapping
    @ApiOperation("获取后三条数据")
    @Cacheable(key = "'selectIndexList'", value = "banner")
    public ResultCommon getAllCrm() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit 3");
        List<CrmBanner> crmBanners = crmBannerService.list(wrapper);

        return ResultCommon.success().setData("items", crmBanners);
    }

}


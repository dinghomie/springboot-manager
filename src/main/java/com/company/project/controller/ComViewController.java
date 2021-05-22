package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.SysFilesEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.ComViewEntity;
import com.company.project.service.ComViewService;



/**
 * 品牌观
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-12 09:19:52
 */
@RestController
@RequestMapping("/comView")
public class ComViewController {
    @Autowired
    private ComViewService comViewService;


    @ApiOperation(value = "新增")
    @PostMapping("add")
    @RequiresPermissions("comView:add")
    @ResponseBody
    public DataResult add(@RequestBody ComViewEntity comView){
        comViewService.save(comView);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete")
    @RequiresPermissions("comView:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comViewService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("update")
    @RequiresPermissions("comView:update")
    @ResponseBody
    public DataResult update(@RequestBody ComViewEntity comView){
        comViewService.updateById(comView);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("listByPage")
    @RequiresPermissions("comView:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComViewEntity comView){
        Page page = new Page(comView.getPage(), comView.getLimit());
        LambdaQueryWrapper<ComViewEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(ComViewEntity::getId, comView.getId());
        queryWrapper.orderByDesc(ComViewEntity::getSort);
        IPage<ComViewEntity> iPage = comViewService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    @ApiOperation(value = "获取详情")
    @PostMapping("getOne")
    @RequiresPermissions("comView:list")
    @ResponseBody
    public DataResult getOne(@RequestBody ComViewEntity comView){
        return DataResult.success(comViewService.getById(comView.getId()));
    }
}

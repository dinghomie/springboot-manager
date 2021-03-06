package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.ComViewEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.ComIndexEntity;
import com.company.project.service.ComIndexService;



/**
 * 首页
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:10
 */
@RestController
@RequestMapping("/")
public class ComIndexController {
    @Autowired
    private ComIndexService comIndexService;


    @ApiOperation(value = "新增")
    @PostMapping("/comIndex/add")
    @RequiresPermissions("comIndex:add")
    @ResponseBody
    public DataResult add(@RequestBody ComIndexEntity comIndex){
        comIndex.setCreateTime(new Date());
        comIndexService.save(comIndex);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/comIndex/delete")
    @RequiresPermissions("comIndex:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comIndexService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/comIndex/update")
    @RequiresPermissions("comIndex:update")
    @ResponseBody
    public DataResult update(@RequestBody ComIndexEntity comIndex){
        comIndexService.updateById(comIndex);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/comIndex/listByPage")
    @RequiresPermissions("comIndex:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComIndexEntity comIndex){
        Page page = new Page(comIndex.getPage(), comIndex.getLimit());
        LambdaQueryWrapper<ComIndexEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(ComIndexEntity::getSort);

        //查询条件示例
        //queryWrapper.eq(ComIndexEntity::getId, comIndex.getId());
        IPage<ComIndexEntity> iPage = comIndexService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "anon-详情")
    @GetMapping("/anon/comIndex/getOne")
    @ResponseBody
    public DataResult getOne(){
        LambdaQueryWrapper<ComIndexEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(ComIndexEntity::getSort);
        List<ComIndexEntity> list =comIndexService.list(queryWrapper);
        if(list.size()>0){
            return DataResult.success(list.get(0));
        }else{
            return DataResult.success();
        }

    }
}

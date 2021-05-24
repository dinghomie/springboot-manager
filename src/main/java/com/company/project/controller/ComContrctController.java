package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

import com.company.project.entity.ComContrctEntity;
import com.company.project.service.ComContrctService;



/**
 * 联系我们
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:11
 */
@RestController
@RequestMapping("/comContrct")
public class ComContrctController {
    @Autowired
    private ComContrctService comContrctService;




    @ApiOperation(value = "新增")
    @PostMapping("/add")
    @RequiresPermissions("comContrct:add")
    @ResponseBody
    public DataResult add(@RequestBody ComContrctEntity comContrct){
        comContrctService.save(comContrct);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("comContrct:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comContrctService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    @RequiresPermissions("comContrct:update")
    @ResponseBody
    public DataResult update(@RequestBody ComContrctEntity comContrct){
        comContrctService.updateById(comContrct);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @RequiresPermissions("comContrct:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComContrctEntity comContrct){
        Page page = new Page(comContrct.getPage(), comContrct.getLimit());
        LambdaQueryWrapper<ComContrctEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(ComContrctEntity::getId, comContrct.getId());
        IPage<ComContrctEntity> iPage = comContrctService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}

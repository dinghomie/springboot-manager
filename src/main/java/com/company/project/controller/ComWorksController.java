package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.ComAboutEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.ComWorksEntity;
import com.company.project.service.ComWorksService;



/**
 * 项目
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:10
 */
@RestController
@RequestMapping("/comWorks")
public class ComWorksController {
    @Autowired
    private ComWorksService comWorksService;


    @ApiOperation(value = "新增")
    @PostMapping("/add")
    @RequiresPermissions("comWorks:add")
    @ResponseBody
    public DataResult add(@RequestBody ComWorksEntity comWorks){
        comWorksService.save(comWorks);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("comWorks:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comWorksService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    @RequiresPermissions("comWorks:update")
    @ResponseBody
    public DataResult update(@RequestBody ComWorksEntity comWorks){
        comWorksService.updateById(comWorks);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @RequiresPermissions("comWorks:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComWorksEntity comWorks){
        Page page = new Page(comWorks.getPage(), comWorks.getLimit());
        LambdaQueryWrapper<ComWorksEntity> queryWrapper = Wrappers.lambdaQuery();
        if(!StringUtils.isEmpty(comWorks.getWorkName())){
            queryWrapper.like(ComWorksEntity::getWorkName, comWorks.getWorkName());

        }
        queryWrapper.orderByDesc(ComWorksEntity::getSort);
        IPage<ComWorksEntity> iPage = comWorksService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}

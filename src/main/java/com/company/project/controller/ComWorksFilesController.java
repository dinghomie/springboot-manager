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

import com.company.project.entity.ComWorksFilesEntity;
import com.company.project.service.ComWorksFilesService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:10
 */
@Controller
@RequestMapping("/")
public class ComWorksFilesController {
    @Autowired
    private ComWorksFilesService comWorksFilesService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/comWorksFiles")
    public String comWorksFiles() {
        return "comworksfiles/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("comWorksFiles/add")
    @RequiresPermissions("comWorksFiles:add")
    @ResponseBody
    public DataResult add(@RequestBody ComWorksFilesEntity comWorksFiles){
        comWorksFilesService.save(comWorksFiles);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("comWorksFiles/delete")
    @RequiresPermissions("comWorksFiles:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comWorksFilesService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("comWorksFiles/update")
    @RequiresPermissions("comWorksFiles:update")
    @ResponseBody
    public DataResult update(@RequestBody ComWorksFilesEntity comWorksFiles){
        comWorksFilesService.updateById(comWorksFiles);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("comWorksFiles/listByPage")
    @RequiresPermissions("comWorksFiles:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComWorksFilesEntity comWorksFiles){
        Page page = new Page(comWorksFiles.getPage(), comWorksFiles.getLimit());
        LambdaQueryWrapper<ComWorksFilesEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(ComWorksFilesEntity::getId, comWorksFiles.getId());
        IPage<ComWorksFilesEntity> iPage = comWorksFilesService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}

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

import com.company.project.entity.ComAboutFilesEntity;
import com.company.project.service.ComAboutFilesService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-12 09:19:52
 */
@Controller
@RequestMapping("/")
public class ComAboutFilesController {
    @Autowired
    private ComAboutFilesService comAboutFilesService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/comAboutFiles")
    public String comAboutFiles() {
        return "comaboutfiles/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("comAboutFiles/add")
    @RequiresPermissions("comAboutFiles:add")
    @ResponseBody
    public DataResult add(@RequestBody ComAboutFilesEntity comAboutFiles){
        comAboutFilesService.save(comAboutFiles);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("comAboutFiles/delete")
    @RequiresPermissions("comAboutFiles:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comAboutFilesService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("comAboutFiles/update")
    @RequiresPermissions("comAboutFiles:update")
    @ResponseBody
    public DataResult update(@RequestBody ComAboutFilesEntity comAboutFiles){
        comAboutFilesService.updateById(comAboutFiles);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("comAboutFiles/listByPage")
    @RequiresPermissions("comAboutFiles:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComAboutFilesEntity comAboutFiles){
        Page page = new Page(comAboutFiles.getPage(), comAboutFiles.getLimit());
        LambdaQueryWrapper<ComAboutFilesEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(ComAboutFilesEntity::getId, comAboutFiles.getId());
        IPage<ComAboutFilesEntity> iPage = comAboutFilesService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}

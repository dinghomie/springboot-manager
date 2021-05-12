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

import com.company.project.entity.ComViewFilesEntity;
import com.company.project.service.ComViewFilesService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-12 09:19:52
 */
@Controller
@RequestMapping("/")
public class ComViewFilesController {
    @Autowired
    private ComViewFilesService comViewFilesService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/comViewFiles")
    public String comViewFiles() {
        return "comviewfiles/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("comViewFiles/add")
    @RequiresPermissions("comViewFiles:add")
    @ResponseBody
    public DataResult add(@RequestBody ComViewFilesEntity comViewFiles){
        comViewFilesService.save(comViewFiles);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("comViewFiles/delete")
    @RequiresPermissions("comViewFiles:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comViewFilesService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("comViewFiles/update")
    @RequiresPermissions("comViewFiles:update")
    @ResponseBody
    public DataResult update(@RequestBody ComViewFilesEntity comViewFiles){
        comViewFilesService.updateById(comViewFiles);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("comViewFiles/listByPage")
    @RequiresPermissions("comViewFiles:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComViewFilesEntity comViewFiles){
        Page page = new Page(comViewFiles.getPage(), comViewFiles.getLimit());
        LambdaQueryWrapper<ComViewFilesEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(ComViewFilesEntity::getId, comViewFiles.getId());
        IPage<ComViewFilesEntity> iPage = comViewFilesService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}

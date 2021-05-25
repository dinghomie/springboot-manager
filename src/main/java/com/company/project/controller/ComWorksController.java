package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.ComAboutEntity;
import com.company.project.entity.SysFilesEntity;
import com.company.project.service.SysFilesService;
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
@RequestMapping("/")
public class ComWorksController {
    @Autowired
    private ComWorksService comWorksService;
    @Autowired
    private SysFilesService sysFilesService;


    @ApiOperation(value = "新增")
    @PostMapping("/comWorks/add")
    @RequiresPermissions("comWorks:add")
    @ResponseBody
    public DataResult add(@RequestBody ComWorksEntity comWorks){
        comWorksService.save(comWorks);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/comWorks/delete")
    @RequiresPermissions("comWorks:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comWorksService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/comWorks/update")
    @RequiresPermissions("comWorks:update")
    @ResponseBody
    public DataResult update(@RequestBody ComWorksEntity comWorks){
        comWorksService.updateById(comWorks);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/comWorks/listByPage")
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
    @ApiOperation(value = "anon-列表")
    @PostMapping("/anon/comWorks/getList")
    @ResponseBody
    public DataResult getList(@RequestBody ComWorksEntity comWorks){
        LambdaQueryWrapper<ComWorksEntity> queryWrapper = Wrappers.lambdaQuery();
        if(!StringUtils.isEmpty(comWorks.getStatus())){
            queryWrapper.eq(ComWorksEntity::getStatus, comWorks.getStatus());
        }
        queryWrapper.orderByDesc(ComWorksEntity::getSort);
        List<ComWorksEntity> list =comWorksService.list(queryWrapper);
        for(ComWorksEntity comWorksEntity:list) {
            LambdaQueryWrapper<SysFilesEntity> queryWrapperSysFilesEntity = Wrappers.lambdaQuery();
            //查询条件示例
            queryWrapperSysFilesEntity.eq(SysFilesEntity::getTableName, "comWorks");
            queryWrapperSysFilesEntity.eq(SysFilesEntity::getTableId, comWorksEntity.getId());
            queryWrapperSysFilesEntity.orderByDesc(SysFilesEntity::getSort);
            List<SysFilesEntity> sysFilesEntityList = sysFilesService.list(queryWrapperSysFilesEntity);

            comWorksEntity.setSysFilesEntityList(sysFilesEntityList);
        }
        return DataResult.success(list);


    }

    @ApiOperation(value = "anon-详情")
    @GetMapping("/anon/comWorks/getOne")
    @ResponseBody
    public DataResult getOne(@RequestParam String id){

        ComWorksEntity comWorksEntity = comWorksService.getById(id);
        LambdaQueryWrapper<SysFilesEntity> queryWrapperSysFilesEntity = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapperSysFilesEntity.eq(SysFilesEntity::getTableName, "comWorks");
        queryWrapperSysFilesEntity.eq(SysFilesEntity::getTableId, comWorksEntity.getId());
        queryWrapperSysFilesEntity.orderByDesc(SysFilesEntity::getSort);
        List<SysFilesEntity> sysFilesEntityList =sysFilesService.list(queryWrapperSysFilesEntity);

        comWorksEntity.setSysFilesEntityList(sysFilesEntityList);
        return DataResult.success(comWorksEntity);
    }
}

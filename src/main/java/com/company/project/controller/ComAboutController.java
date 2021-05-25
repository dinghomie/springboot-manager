package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.ComIndexEntity;
import com.company.project.entity.ComViewEntity;
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

import com.company.project.entity.ComAboutEntity;
import com.company.project.service.ComAboutService;



/**
 * 关于我们
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-12 09:19:52
 */
@Controller
@RequestMapping("/")
public class ComAboutController {
    @Autowired
    private ComAboutService comAboutService;
    @Autowired
    private SysFilesService sysFilesService;




    @ApiOperation(value = "新增")
    @PostMapping("/comAbout/add")
    @RequiresPermissions("comAbout:add")
    @ResponseBody
    public DataResult add(@RequestBody ComAboutEntity comAbout){
        comAboutService.save(comAbout);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/comAbout/delete")
    @RequiresPermissions("comAbout:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comAboutService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/comAbout/update")
    @RequiresPermissions("comAbout:update")
    @ResponseBody
    public DataResult update(@RequestBody ComAboutEntity comAbout){
        comAboutService.updateById(comAbout);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/comAbout/listByPage")
    @RequiresPermissions("comAbout:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComAboutEntity comAbout){
        Page page = new Page(comAbout.getPage(), comAbout.getLimit());
        LambdaQueryWrapper<ComAboutEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if(!StringUtils.isEmpty(comAbout.getName())){
            queryWrapper.like(ComAboutEntity::getName, comAbout.getName());

        }
        queryWrapper.orderByDesc(ComAboutEntity::getSort);

        IPage<ComAboutEntity> iPage = comAboutService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "anon-列表")
    @PostMapping("/anon/comAbout/getList")
    @ResponseBody
    public DataResult getList(){
        LambdaQueryWrapper<ComAboutEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(ComAboutEntity::getSort);
        List<ComAboutEntity> list =comAboutService.list(queryWrapper);
            for(ComAboutEntity comAboutEntity:list){
                LambdaQueryWrapper<SysFilesEntity> queryWrapperSysFilesEntity = Wrappers.lambdaQuery();
                //查询条件示例
                queryWrapperSysFilesEntity.eq(SysFilesEntity::getTableName, "comAbout");
                queryWrapperSysFilesEntity.eq(SysFilesEntity::getTableId, comAboutEntity.getId());
                queryWrapperSysFilesEntity.orderByDesc(SysFilesEntity::getSort);
                List<SysFilesEntity> sysFilesEntityList =sysFilesService.list(queryWrapperSysFilesEntity);

                comAboutEntity.setSysFilesEntityList(sysFilesEntityList);
            }

            return DataResult.success(list);

    }

    @ApiOperation(value = "anon-详情")
    @GetMapping("/anon/comAbout/getOne")
    @ResponseBody
    public DataResult getOne(@RequestParam String id){

        ComAboutEntity comAboutEntity = comAboutService.getById(id);
        LambdaQueryWrapper<SysFilesEntity> queryWrapperSysFilesEntity = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapperSysFilesEntity.eq(SysFilesEntity::getTableName, "comAbout");
        queryWrapperSysFilesEntity.eq(SysFilesEntity::getTableId, comAboutEntity.getId());
        queryWrapperSysFilesEntity.orderByDesc(SysFilesEntity::getSort);
        List<SysFilesEntity> sysFilesEntityList =sysFilesService.list(queryWrapperSysFilesEntity);

        comAboutEntity.setSysFilesEntityList(sysFilesEntityList);
        return DataResult.success(comAboutEntity);
    }
}

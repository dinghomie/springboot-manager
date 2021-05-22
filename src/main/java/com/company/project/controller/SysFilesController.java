package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.common.utils.DataResult;
import com.company.project.entity.ComViewEntity;
import com.company.project.entity.ComWorksFilesEntity;
import com.company.project.entity.SysFilesEntity;
import com.company.project.service.SysFilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


/**
 * 文件上传
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@RestController
@RequestMapping("/sysFiles")
@Api(tags = "文件管理")
public class SysFilesController {
    @Resource
    private SysFilesService sysFilesService;

    @ApiOperation(value = "新增")
    @PostMapping("/upload")
    @RequiresPermissions(value = {"sysFiles:add", "sysContent:update", "sysContent:add"}, logical = Logical.OR)
    public DataResult add(@RequestParam(value = "file") MultipartFile file) {
        //判断文件是否空
        if (file == null || file.getOriginalFilename() == null || "".equalsIgnoreCase(file.getOriginalFilename().trim())) {
            return DataResult.fail("文件为空");
        }
        return sysFilesService.saveFile(file);
    }

    @ApiOperation(value = "更新")
    @PutMapping("update")
    @RequiresPermissions("sysFiles:delete")
    @ResponseBody
    public DataResult update(@RequestBody SysFilesEntity sysFiles){
        sysFilesService.updateById(sysFiles);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("sysFiles:delete")
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
        sysFilesService.removeByIdsAndFiles(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @RequiresPermissions("sysFiles:list")
    public DataResult findListByPage(@RequestBody SysFilesEntity sysFiles) {
        Page page = new Page(sysFiles.getPage(), sysFiles.getLimit());
        LambdaQueryWrapper<SysFilesEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if(!StringUtils.isEmpty(sysFiles.getTableName())){
            queryWrapper.eq(SysFilesEntity::getTableName, sysFiles.getTableName());

        }
        if(!StringUtils.isEmpty(sysFiles.getTableId())){
            queryWrapper.eq(SysFilesEntity::getTableId, sysFiles.getTableId());
        }
        queryWrapper.orderByDesc(SysFilesEntity::getSort);
        IPage<SysFilesEntity> iPage = sysFilesService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }


}

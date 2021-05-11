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

import com.company.project.entity.ComAboutEntity;
import com.company.project.service.ComAboutService;



/**
 * 关于我们
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-05-11 17:18:11
 */
@Controller
@RequestMapping("/")
public class ComAboutController {
    @Autowired
    private ComAboutService comAboutService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/comAbout")
    public String comAbout() {
        return "comabout/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("comAbout/add")
    @RequiresPermissions("comAbout:add")
    @ResponseBody
    public DataResult add(@RequestBody ComAboutEntity comAbout){
        comAboutService.save(comAbout);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("comAbout/delete")
    @RequiresPermissions("comAbout:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        comAboutService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("comAbout/update")
    @RequiresPermissions("comAbout:update")
    @ResponseBody
    public DataResult update(@RequestBody ComAboutEntity comAbout){
        comAboutService.updateById(comAbout);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("comAbout/listByPage")
    @RequiresPermissions("comAbout:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ComAboutEntity comAbout){
        Page page = new Page(comAbout.getPage(), comAbout.getLimit());
        LambdaQueryWrapper<ComAboutEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(ComAboutEntity::getId, comAbout.getId());
        IPage<ComAboutEntity> iPage = comAboutService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}

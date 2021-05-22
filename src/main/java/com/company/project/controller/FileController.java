package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.common.file.FileRepository;
import com.company.project.common.utils.DataResult;
import com.company.project.entity.ComAboutEntity;
import com.company.project.service.ComAboutService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileRepository fileRepository;


    @ApiOperation(value = "文件上传")
    @PostMapping("/uploadFile")
    public DataResult uploadFile(MultipartFile file,String tableId,String tableName, HttpServletRequest request){
        return fileRepository.uploadFile(file,tableId,tableName,request);
    }

    //多文件上传
    @ApiOperation(value = "文件上传")
    @PostMapping("/uploadFiles")
    public DataResult uploadFile(List<MultipartFile> files){
        return fileRepository.uploadFiles(files);
    }

}

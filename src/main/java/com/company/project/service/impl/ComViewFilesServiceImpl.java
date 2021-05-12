package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ComViewFilesMapper;
import com.company.project.entity.ComViewFilesEntity;
import com.company.project.service.ComViewFilesService;


@Service("comViewFilesService")
public class ComViewFilesServiceImpl extends ServiceImpl<ComViewFilesMapper, ComViewFilesEntity> implements ComViewFilesService {


}
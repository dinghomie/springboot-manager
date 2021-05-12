package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ComAboutFilesMapper;
import com.company.project.entity.ComAboutFilesEntity;
import com.company.project.service.ComAboutFilesService;


@Service("comAboutFilesService")
public class ComAboutFilesServiceImpl extends ServiceImpl<ComAboutFilesMapper, ComAboutFilesEntity> implements ComAboutFilesService {


}
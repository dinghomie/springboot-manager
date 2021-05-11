package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ComWorksFilesMapper;
import com.company.project.entity.ComWorksFilesEntity;
import com.company.project.service.ComWorksFilesService;


@Service("comWorksFilesService")
public class ComWorksFilesServiceImpl extends ServiceImpl<ComWorksFilesMapper, ComWorksFilesEntity> implements ComWorksFilesService {


}
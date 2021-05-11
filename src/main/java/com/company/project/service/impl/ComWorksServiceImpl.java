package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ComWorksMapper;
import com.company.project.entity.ComWorksEntity;
import com.company.project.service.ComWorksService;


@Service("comWorksService")
public class ComWorksServiceImpl extends ServiceImpl<ComWorksMapper, ComWorksEntity> implements ComWorksService {


}
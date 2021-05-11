package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ComAboutMapper;
import com.company.project.entity.ComAboutEntity;
import com.company.project.service.ComAboutService;


@Service("comAboutService")
public class ComAboutServiceImpl extends ServiceImpl<ComAboutMapper, ComAboutEntity> implements ComAboutService {


}
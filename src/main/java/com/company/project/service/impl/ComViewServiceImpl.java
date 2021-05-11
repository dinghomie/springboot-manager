package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ComViewMapper;
import com.company.project.entity.ComViewEntity;
import com.company.project.service.ComViewService;


@Service("comViewService")
public class ComViewServiceImpl extends ServiceImpl<ComViewMapper, ComViewEntity> implements ComViewService {


}
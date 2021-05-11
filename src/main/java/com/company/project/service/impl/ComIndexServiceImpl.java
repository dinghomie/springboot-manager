package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ComIndexMapper;
import com.company.project.entity.ComIndexEntity;
import com.company.project.service.ComIndexService;


@Service("comIndexService")
public class ComIndexServiceImpl extends ServiceImpl<ComIndexMapper, ComIndexEntity> implements ComIndexService {


}
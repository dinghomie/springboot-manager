package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ComContrctMapper;
import com.company.project.entity.ComContrctEntity;
import com.company.project.service.ComContrctService;


@Service("comContrctService")
public class ComContrctServiceImpl extends ServiceImpl<ComContrctMapper, ComContrctEntity> implements ComContrctService {


}
package com.database.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.database.entity.Ord;
import com.database.mapper.OrdMapper;
import com.database.service.IOrdService;

@Service
public class OrdServiceImpl extends ServiceImpl<OrdMapper, Ord> implements IOrdService {

}

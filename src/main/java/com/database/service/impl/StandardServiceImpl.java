package com.database.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.database.entity.Standard;
import com.database.mapper.StandardMapper;
import com.database.service.IStandardService;

@Service
public class StandardServiceImpl extends ServiceImpl<StandardMapper, Standard> implements IStandardService {

}

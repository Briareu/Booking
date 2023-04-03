package com.database.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.database.entity.Pic;
import com.database.mapper.PicMapper;
import com.database.service.IPicService;

@Service
public class PicServiceImpl extends ServiceImpl<PicMapper, Pic> implements IPicService {

}

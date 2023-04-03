package com.database.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.database.entity.Like;
import com.database.mapper.LikeMapper;
import com.database.service.ILikeService;

@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements ILikeService {

}

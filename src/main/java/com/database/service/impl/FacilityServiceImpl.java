package com.database.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.database.entity.Facility;
import com.database.mapper.FacilityMapper;
import com.database.service.IFacilityService;

@Service
public class FacilityServiceImpl extends ServiceImpl<FacilityMapper, Facility> implements IFacilityService {

}

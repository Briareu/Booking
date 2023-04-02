package com.database.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.database.entity.TblStudent;
import com.database.mapper.TblStudentMapper;
import com.database.service.ITblStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxw
 * @since 2019-09-25
 */
@Service
public class TblStudentServiceImpl extends ServiceImpl<TblStudentMapper, TblStudent> implements ITblStudentService {

    private TblStudentMapper tblStudentMapper;

    @Autowired
    public TblStudentServiceImpl(TblStudentMapper tblStudentMapper){
        this.tblStudentMapper = tblStudentMapper;
    }

    public TblStudentServiceImpl( ){

    }
}
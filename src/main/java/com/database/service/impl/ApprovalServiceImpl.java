package com.database.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.database.entity.Approval;
import com.database.mapper.ApprovalMapper;
import com.database.service.IApprovalService;

@Service
public class ApprovalServiceImpl extends ServiceImpl<ApprovalMapper, Approval> implements IApprovalService {

}

package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.service.IStandardService;

/**
 * 我还没想好放什么
 * 前端控制器
 * @author RONG
 *
 */
@RestController
@RequestMapping("/standard")
@CrossOrigin
public class StandardController {
	@Autowired
	private IStandardService standardService;
	
	
}

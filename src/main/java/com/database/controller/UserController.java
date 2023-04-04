package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.User;
import com.database.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * user控制器
 * @author RONG
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 根据手机号进行注册，只对手机号进行判重
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/registter")
	public String register(@RequestBody User user) {
		System.out.println(user.getName());
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", user.getPhone());
		if(userService.getOne(queryWrapper) != null) {
			return "该用户名已经存在";
		}

		User newuser = new User();
		newuser.setGender(user.getGender());
		newuser.setMail(user.getMail());
		newuser.setName(user.getName());
		newuser.setPhone(user.getPhone());
		newuser.setPwd(user.getPwd());
		newuser.setState(user.getState());
		userService.save(newuser);
		return "注册成功!";
	}
	/*
	@PostMapping(path = "/login")
	public Object login(@RequestBody User loginuser) {
		System.out.println(loginuser.toString());
		String mail = loginuser.getMail();
		
	}*/
}

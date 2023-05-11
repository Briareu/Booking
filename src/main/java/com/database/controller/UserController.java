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

/**
 * user控制器
 * @author RONG
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 根据手机号进行注册，只对手机号进行判重
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/register")
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
	
	/**
	 * 使用手机号和密码进行登陆验证
	 * 通过验证的用户状态直接更新为1
	 * @param loginuser
	 * @return
	 */
	@PostMapping(path = "/login")
	public Object login(@RequestBody User loginuser) {
		System.out.println(loginuser.toString());
		Integer phone = loginuser.getPhone();
		String pwd = loginuser.getPwd();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", phone);
		if(userService.getOne(queryWrapper) == null) {
			return "nouser";
		}
		User user = userService.getOne(queryWrapper);
		if(user.getPwd().equals(pwd)) {
			user.setState(1);
			return user;
		}
		return "wrong";
	}
	
	/**
	 * 根据uid更新密码
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/changePwd")
	public String changePwd(@RequestBody User user) {
		Integer uid = user.getUid();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		if(userService.getOne(queryWrapper) == null) {
			return "nouser";
		}
		
		User theuser = userService.getOne(queryWrapper);
		theuser.setPwd(user.getPwd());
		userService.updateById(theuser);
		return "success";
	}
	
	/**
	 * 根据id更新性别、邮箱、用户名称
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/changeProfile")
	public String changeProfile(@RequestBody User user) {
		Integer uid = user.getUid();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		if(userService.getOne(queryWrapper) == null) {
			return "nouser";
		}
		
		User theuser = userService.getOne(queryWrapper);
		
		theuser.setGender(user.getGender());
		theuser.setMail(user.getMail());
		theuser.setName(user.getName());
		userService.updateById(theuser);
		return "success";
	}
	
	/**
	 * 更新用户状态（根据传入的user进行更新）
	 * @param user
	 * @return
	 */
	@PostMapping(path = "changeState")
	public String changeState(@RequestBody User user) {
		Integer uid = user.getUid();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		if(userService.getOne(queryWrapper) == null) {
			return "nouser";
		}
		
		User theuser = userService.getOne(queryWrapper);
		theuser.setState(user.getState());
		userService.updateById(theuser);
		return "success";
	}
}

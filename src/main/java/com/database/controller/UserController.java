package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.Message;
import com.database.entity.User;
import com.database.service.IUserService;

/**
 * user控制器
 * @author RONG
 * @since 2023-04-03
 * 
 * tested 5-12 under new pom and message(under entity)-using
 */
@RestController
@RequestMapping(value = "/user", produces = {"text/html;charset=utf8", "application/json;charset=utf8"})
@CrossOrigin
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 根据手机号进行注册，只对手机号进行判重
	 * @param user
	 * @return
	 * 
	 * 05-11fixed
	 */
	@PostMapping(path = "/register")
	public Message register(@RequestBody User user) {
		Message message = new Message();
		System.out.println(user.getName());
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", user.getPhone());
		if(userService.getOne(queryWrapper) != null) {
			message.setMessage("该用户名已经存在");
			return message;
		}
		
		if(user.getPhone().length() != 11) {
			message.setMessage("手机号格式错误");
			return message;
		}
		
		User newuser = new User();
		newuser.setGender(user.getGender());
		newuser.setMail(user.getMail());
		newuser.setName(user.getName());
		newuser.setPhone(user.getPhone());
		newuser.setPwd(user.getPwd());
		newuser.setState(user.getState());
		userService.save(newuser);
		message.setMessage("注册成功");
		return message;
	}
	
	/**
	 * 使用手机号和密码进行登陆验证
	 * 通过验证的用户状态直接更新为1
	 * @param loginuser
	 * @return
	 */
	@PostMapping(path = "/login")
	public Object login(@RequestBody User loginuser) {
		Message message = new Message();
		System.out.println(loginuser.toString());
		String phone = loginuser.getPhone();
		String pwd = loginuser.getPwd();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", phone);
		if(userService.getOne(queryWrapper) == null) {
			message.setMessage("nouser");
			return message;
		}
		User user = userService.getOne(queryWrapper);
		if(user.getPwd().equals(pwd)) {
			user.setState(1);
			userService.updateById(user);
			return user;
		}
		message.setMessage("wrong");
		return message;
	}
	
	/**
	 * 根据uid更新密码
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/changePwd")
	public Message changePwd(@RequestBody User user) {
		Message message = new Message();
		Integer uid = user.getUid();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		if(userService.getOne(queryWrapper) == null) {
			message.setMessage("nouser");
			return message;
		}
		
		User theuser = userService.getOne(queryWrapper);
		theuser.setPwd(user.getPwd());
		userService.updateById(theuser);
		message.setMessage("success");
		return message;
	}
	
	/**
	 * 根据id更新性别、邮箱、用户名称
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/changeProfile")
	public Message changeProfile(@RequestBody User user) {
		Message message = new Message();
		Integer uid = user.getUid();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		if(userService.getOne(queryWrapper) == null) {
			message.setMessage("nouser");
			return message;
		}
		
		User theuser = userService.getOne(queryWrapper);
		
		theuser.setGender(user.getGender());
		theuser.setMail(user.getMail());
		theuser.setName(user.getName());
		userService.updateById(theuser);
		message.setMessage("success");
		return message;
	}
	
	/**
	 * 更新用户状态（根据传入的user进行更新）
	 * @param user
	 * @return
	 */
	@PostMapping(path = "changeState")
	public Message changeState(@RequestBody User user) {
		Message message = new Message();
		Integer uid = user.getUid();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		if(userService.getOne(queryWrapper) == null) {
			message.setMessage("nouser");
			return message;
		}
		
		User theuser = userService.getOne(queryWrapper);
		theuser.setState(user.getState());
		userService.updateById(theuser);
		message.setMessage("success");
		return message;
	}
	
	/**
	 * 根据uid获取state
	 * @param uid
	 * @return
	 */
	@GetMapping(path = "/getStateById")
	public Message getStateById(@RequestParam("uid") Integer uid) {
		Message message = new Message();
		System.out.println(uid);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		User thisUser = userService.getOne(queryWrapper);
		if(thisUser == null) {
			message.setMessage("nouser");
			return message;
		}
		message.setMessage(Integer.toString(thisUser.getState()));
		return message;
	}
	
	/**
	 * 根据phone获取state
	 * @param phone
	 * @return
	 */
	@GetMapping(path = "/getStateByPno")
	public Message getStateByPno(@RequestParam("phone") String phone) {
		Message message = new Message();
		System.out.println(phone);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", phone);
		User thisUser = userService.getOne(queryWrapper);
		if(thisUser == null) {
			message.setMessage("nouser");
			return message;
		}
		message.setMessage(Integer.toString(thisUser.getState()));
		return message;
	}
	
	/**
	 * 根据uid返回User
	 * @param uid
	 * @return
	 */
	@GetMapping(path = "/getUser")
	public User getUser(@RequestParam("uid") Integer uid) {
		System.out.println(uid);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		User thisUser = userService.getOne(queryWrapper);
		if(thisUser == null) {
			return null;
		}
		return thisUser;
	}
}
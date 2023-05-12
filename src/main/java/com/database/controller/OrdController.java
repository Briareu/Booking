package com.database.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.Message;
import com.database.entity.Ord;
import com.database.entity.Room;
import com.database.service.IOrdService;
import com.database.service.IRoomService;

/**
 * 大概增删改查
 * @author RONG
 * @since 4/12/2023
 * 
 * tested 05-12 under new pom
 */

@RestController
@RequestMapping(value = "/ord", produces = {"text/html;charset=utf8", "application/json;charset=utf8"})
@CrossOrigin
public class OrdController {

	@Autowired
	private IOrdService orderService;
	
	@Autowired
	private IRoomService standardService;
	
	@GetMapping("/getByUid")
	public List<Ord> getByUid(@RequestParam("uid") Integer uid){
		System.err.println(uid);
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		List<Ord> orders = orderService.list(queryWrapper);
		
		if(orders == null) {
			return null;
		}else {
			return orders;
		}
	}
	
	/**
	 * 根据uid，state筛选order
	 * @param uid
	 * @param state
	 * @return
	 */
	@GetMapping("/getByUidAndState")
	public List<Ord> getByUidAndState(@RequestParam("uid") Integer uid, @RequestParam("state") String state){
		System.err.println(uid);
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid).eq("state", state);
		List<Ord> orders = orderService.list(queryWrapper);
		
		if(orders == null) {
			return null;
		}else {
			return orders;
		}
	}
	
	/**
	 * 根据hid返回order
	 * @param hid
	 * @return
	 */
	@GetMapping("/getByhid")
	public List<Ord> getByhid(@RequestParam("hid") Integer hid){
		System.out.println(hid);
		QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("hid", hid);
		List<Room> standards = standardService.list(queryWrapper);//it is called room now
		List<Ord> orders = new ArrayList<>();
		
		if(standards == null) {
			return null;
		}
		
		for(Room itr : standards) {
			QueryWrapper<Ord> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("sid", itr.getSid());
			List<Ord> tmp = orderService.list(queryWrapper2);
			orders.addAll(tmp);
		}
		
		return orders;
	}
	
	/**
	 * 添加
	 * @param order
	 * @return
	 */
	@PostMapping("/addOrder")
	public Message addOrder(@RequestBody Ord order,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
		Ord neword = new Ord();
		neword.setEndTime(endTime);
		neword.setSid(order.getSid());
		neword.setStartTime(startTime);
		neword.setState(order.getState());
		neword.setTotalPrice(order.getTotalPrice());
		neword.setUid(order.getUid());
		orderService.save(neword);
		Message message = new Message();
		message.setMessage("success");
		return message;
	}
	
	/**
	 * 根据oid更新
	 * @param order
	 * @return
	 */
	@PostMapping("/changeOrder")
	public Message changeOrder(@RequestBody Ord order,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
		Message message = new Message();
		System.err.println(order.getOid());
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("oid", order.getOid());
		if(orderService.getOne(queryWrapper) == null) {
			message.setMessage("noorder");
			return message;
		}else {
			Ord theorder = orderService.getOne(queryWrapper);
			theorder.setEndTime(endTime);
			theorder.setSid(order.getSid());
			theorder.setStartTime(startTime);
			theorder.setState(order.getState());
			theorder.setTotalPrice(order.getTotalPrice());
			theorder.setUid(order.getUid());
			orderService.updateById(theorder);
			message.setMessage("success");
			return message;
		}
	}
	
	/**
	 * 根据oid删除order
	 * @param oid
	 * @return
	 */
	@GetMapping("/delOrder")
	public boolean delOrder(@RequestParam("oid") Integer oid) {
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("oid", oid);
		boolean del = orderService.remove(queryWrapper);
		return del;
	}
}

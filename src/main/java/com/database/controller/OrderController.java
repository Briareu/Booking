package com.database.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.Order;
import com.database.entity.Standard;
import com.database.service.IOrderService;
import com.database.service.IStandardService;

/**
 * 大概增删改查
 * @author RONG
 * @since 4/12/2023
 */
@RestController
@RequestMapping(value = "/order", produces = "text/html;charset=utf8")
@CrossOrigin
@ResponseBody
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IStandardService standardService;
	
	@GetMapping("/getByUid")
	public List<Order> getByUid(@RequestParam("uid") Integer uid){
		System.err.println(uid);
		QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		List<Order> orders = orderService.list(queryWrapper);
		
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
	public List<Order> getByUidAndState(@RequestParam("uid") Integer uid, @RequestParam("state") String state){
		System.err.println(uid);
		QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid).eq("state", state);
		List<Order> orders = orderService.list(queryWrapper);
		
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
	public List<Order> getByhid(@RequestParam("hid") Integer hid){
		System.out.println(hid);
		QueryWrapper<Standard> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("hid", hid);
		List<Standard> standards = standardService.list(queryWrapper);
		List<Order> orders = new ArrayList<>();
		
		if(standards == null) {
			return null;
		}
		
		for(Standard itr : standards) {
			QueryWrapper<Order> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("sid", itr.getSid());
			List<Order> tmp = orderService.list(queryWrapper2);
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
	public String addOrder(@RequestBody Order order) {
		System.err.println(order.getHid());
		orderService.save(order);
		return "注册成功";
	}
	
	/**
	 * 根据oid更新
	 * @param order
	 * @return
	 */
	@PostMapping("/changeOrder")
	public String changeOrder(@RequestBody Order order) {
		System.err.println(order.getOid());
		QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("oid", order.getOid());
		if(orderService.getOne(queryWrapper) == null) {
			return "noOrder";
		}else {
			Order theorder = orderService.getOne(queryWrapper);
			theorder.setEndTime(order.getEndTime());
			theorder.setHid(order.getHid());
			theorder.setSid(order.getSid());
			theorder.setStartTime(order.getStartTime());
			theorder.setState(order.getState());
			theorder.setTotalPrice(order.getTotalPrice());
			theorder.setUid(order.getUid());
			orderService.updateById(theorder);
			return "success";
		}
	}
	
	/**
	 * 根据oid删除order
	 * @param oid
	 * @return
	 */
	@GetMapping("/delOrder")
	public boolean delOrder(@RequestParam("oid") Integer oid) {
		QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("oid", oid);
		boolean del = orderService.remove(queryWrapper);
		return del;
	}
}

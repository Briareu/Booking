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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.Message;
import com.database.entity.Ord;
import com.database.entity.Room;
import com.database.service.IOrdService;
import com.database.service.IRoomService;

import cn.hutool.core.lang.UUID;

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
@ResponseBody
public class OrdController {

	@Autowired
	private IOrdService orderService;
	
	@Autowired
	private IRoomService standardService;
	
	/**
	 * @param uid
	 * @param sortreq
	 * @param sortreq
	 * @return
	 */
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
	
	@GetMapping("/getByOrdseq")
	public Object getByOrdseq(@RequestParam("ordseq") String ordseq){
		Message message = new Message();
		System.err.println(ordseq);
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ordseq", ordseq);
		Ord ord = orderService.getOne(queryWrapper);
		
		if(ord == null) {
			message.setMessage("noord");
			return message;
		}else {
			return ord;
		}
	}
	
	/**
	 * 
	 * @param sid
	 * @return
	 */
	@GetMapping("/getBySid")
	public List<Ord> getBySid(@RequestParam("sid") Integer sid
			){
		System.err.println(sid);
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sid", sid);
		List<Ord> orders = orderService.list(queryWrapper);
		
		if(orders == null) {
			return null;
		}else {
			return orders;
		}
	}
	/**
	 * sortreq = 0, 不排序；
	 * sortreq = 1, 订单开始时间；
	 * sortreq = 2, 订单创建时间；
	 * 根据uid，state筛选order
	 * @param uid
	 * @param state
	 * @param sortreq
	 * @return
	 */
	@GetMapping("/getByUidAndState")
	public List<Ord> getByUidAndState(@RequestParam("uid") Integer uid, @RequestParam("state") String state
			//, @RequestParam("sortreq") Integer sortreq
			){
		System.err.println(uid);
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid).eq("state", state);
		List<Ord> orders = orderService.list(queryWrapper);
		
		if(orders == null) {
			return null;
		}else {
			/*if(sortreq == 1) {
				orders.sort((t1, t2) -> t2.getStartTime().compareTo(t1.getStartTime()));
			}else if(sortreq == 2) {
				orders.sort((t1, t2) -> t2.getCreateTime().compareTo(t1.getCreateTime()));
			}*/
			return orders;
		}
	}
	
	/**
	 * sortreq = 0, 不排序；
	 * sortreq = 1, 订单开始时间；
	 * sortreq = 2, 订单创建时间；
	 * 根据hid返回order
	 * @param uid
	 * @param hid
	 * @param sortreq
	 * @return
	 */
	@GetMapping("/getByUidAndHid")
	public List<Ord> getByUidAndHid(@RequestParam("uid") Integer uid, @RequestParam("hid") Integer hid
			// @RequestParam("sortreq") Integer sortreq
			){
		System.out.println(hid);
		QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid).eq("hid", hid);
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
		
		/*if(sortreq == 1) {
			orders.sort((t1, t2) -> t2.getStartTime().compareTo(t1.getStartTime()));
		}else if(sortreq == 2) {
			orders.sort((t1, t2) -> t2.getCreateTime().compareTo(t1.getCreateTime()));
		}*/
		return orders;
	}
	
	/**
	 * sortreq = 0, 不排序；
	 * sortreq = 1, 订单开始时间；
	 * sortreq = 2, 订单创建时间；
	 * 根据hid返回order
	 * @param hid
	 * @param sortreq
	 * @return
	 */
	@GetMapping("/getByhid")
	public List<Ord> getByhid(@RequestParam("hid") Integer hid
			//, @RequestParam("sortreq") Integer sortreq
			){
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
		
		/*if(sortreq == 1) {
			orders.sort((t1, t2) -> t2.getStartTime().compareTo(t1.getStartTime()));
		}else if(sortreq == 2) {
			orders.sort((t1, t2) -> t2.getCreateTime().compareTo(t1.getCreateTime()));
		}*/
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
		neword.setNum(order.getNum());
		
		long time = System.currentTimeMillis();
		int random = (int)(Math.random() * Integer.MAX_VALUE);
		UUID uuid = new UUID(time, random);
		neword.setOrdseq(uuid.toString().replace("-", "").toLowerCase());
		
		orderService.save(neword);
		Message message = new Message();
		message.setMessage("success");
		return message;
	}
	
	/**
	 * 添加
	 * @param order
	 * @return
	 */
	@PostMapping("/addYuding")
	public Message addYuding(@RequestBody Ord order) {
		Ord neword = new Ord();
		neword.setSid(order.getSid());
		neword.setState(order.getState());
		neword.setTotalPrice(order.getTotalPrice());
		neword.setUid(order.getUid());
		neword.setNum(order.getNum());
		
		long time = System.currentTimeMillis();
		int random = (int)(Math.random() * Integer.MAX_VALUE);
		UUID uuid = new UUID(time, random);
		neword.setOrdseq(uuid.toString().replace("-", "").toLowerCase());
		
		orderService.save(neword);
		Message message = new Message();
		message.setMessage("success");
		return message;
	}
	
	
	/**
	 * 根据oid更新
	 * @param order
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@PostMapping("/changeOrder")
	public Message changeOrder(@RequestBody Ord order,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, 
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
		Message message = new Message();
		System.err.println(order.getOid());
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ordseq", order.getOrdseq());
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
	 * 
	 * @param ordseq
	 * @param state
	 * @return
	 */
	@GetMapping("/changeState")
	public Message changeState(@RequestParam("ordseq") String ordseq, @RequestParam("state") String state) {
		Message message = new Message();
		System.err.println(ordseq);
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ordseq", ordseq);
		if(orderService.getOne(queryWrapper) == null) {
			message.setMessage("noorder");
			return message;
		}else {
			Ord theorder = orderService.getOne(queryWrapper);
			theorder.setState(state);
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
	public boolean delOrder(@RequestParam("ordseq") String ordseq) {
		QueryWrapper<Ord> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ordseq", ordseq);
		boolean del = orderService.remove(queryWrapper);
		return del;
	}
}

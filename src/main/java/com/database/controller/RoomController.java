package com.database.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.database.entity.Hotel;
import com.database.entity.Ord;
import com.database.entity.Room;
import com.database.service.IHotelService;
import com.database.service.IOrdService;
import com.database.service.IRoomService;

/**
 * 放了一点
 * 前端控制器
 * @author RONG
 *
 * tested 05-12 under new pom and message-using
 */
@RestController
@RequestMapping(value = "/room", produces = {"text/html;charset=utf8", "application/json;charset=utf8"})
@CrossOrigin
public class RoomController {
	@Autowired
	private IRoomService standardService;
	
	@Autowired
	private IHotelService hotelService;
	
	@Autowired
	private IOrdService ordService;
	
	/**
	 * 获取hotel全部standard信息
	 * @param hid
	 * @return
	 * 05-11checked
	 */
	@GetMapping(value = "/getByHotel")
	public List<Room> getByHotel(@RequestParam("hid") Integer hid){
		System.err.println(hid);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("hid", hid);
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		for(Room r : standards) {
			r.setPriceInt(Integer.valueOf(r.getPrice().replace("元", "").replace(",", "")));
		}
		return standards;
	}
	/**
	 * 
	 * @param sid
	 * @return
	 */
	@GetMapping(value = "/getBySid")
	public List<Room> getBySid(@RequestParam("sid") Integer sid){
		System.err.println(sid);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("sid", sid);
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		for(Room r : standards) {
			r.setPriceInt(Integer.valueOf(r.getPrice().replace("元", "").replace(",", "")));
		}
		return standards;
	}
	/**
	 * 根据标准返回房型
	 * @param description
	 * @return
	 * 
	 * 05-11fixed
	 */
	@GetMapping(path = "/getByDescription")
	public List<Room> getByDescription(@RequestParam("des") String des){
		System.err.println(des);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.like("des", des);
		List<Room> standards = standardService.list(queryWrapper1);
		if(standards == null) {
			return null;
		}
		for(Room r : standards) {
			r.setPriceInt(Integer.valueOf(r.getPrice().replace("元", "").replace(",", "")));
		}
		return standards;
	}
	
	/**
	 * 根据标准返回酒店
	 * @param des
	 * @return
	 * 
	 * 05-11fixed
	 */
	@GetMapping(path = "/getHotelByDescription")
	public List<Hotel> getHotelByDescription(@RequestParam("des") String des){
		System.err.println(des);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.like("des", des);
		List<Room> standards = standardService.list(queryWrapper1);
		if(standards == null) {
			return null;
		}
		
		List<Hotel> hotels = new ArrayList<>();
		for(Room s : standards) {
			QueryWrapper<Hotel> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("hid", s.getHid());
			Hotel tmp = hotelService.getOne(queryWrapper2);
			hotels.add(tmp);
		}
		
		return hotels;
	}
	
	/**
	 * 根据人数介于paramNum1和paramNum2条件来返回standard
	 * @param peopleNum1
	 * @param peopleNum2
	 * @return
	 * 
	 * 05-11fixed
	 */
	@GetMapping(path = "/getByPeopelNum")
	public List<Room> getByPeopleNum(@RequestParam("peopleNum1") String peopleNum1, @RequestParam("peopleNum2") String peopleNum2){
		System.err.println(peopleNum1);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.between("peopleNum", peopleNum1, peopleNum2);
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		
		/*List<Hotel> hotels = new ArrayList<>();
		for(Standard s: standards) {
			QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hid", s.getHid());
			List<Hotel> tmp = hotelService.list(queryWrapper);
			hotels.addAll(tmp);
		}
		
		return hotels;*/
		for(Room r : standards) {
			r.setPriceInt(Integer.valueOf(r.getPrice().replace("元", "").replace(",", "")));
		}
		return standards;
	}
	
	/**
	 * 根据人数介于paramNum1和paramNum2条件来返回hotel
	 * @param peopleNum1
	 * @param peopleNum2
	 * @return
	 * 
	 * 05-11fixed
	 */
	@GetMapping(path = "/getHotelByPeopelNum")
	public List<Hotel> getHotelByPeopleNum(@RequestParam("peopleNum1") String peopleNum1, @RequestParam("peopleNum2") String peopleNum2){
		System.err.println(peopleNum1);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.between("peopleNum", peopleNum1, peopleNum2);
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		
		List<Hotel> hotels = new ArrayList<>();
		for(Room s: standards) {
			QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hid", s.getHid());
			List<Hotel> tmp = hotelService.list(queryWrapper);
			hotels.addAll(tmp);
		}
		
		return hotels;
	}
	
	/**
	 * 根据price介于price1和price2返回standard
	 * @param price1
	 * @param price2
	 * @return
	 * 
	 * 05-11fixed
	 */
	@GetMapping(path = "/getByPrice")
	public List<Room> getByPrice(@RequestParam("price1") String price1, @RequestParam("price2") String price2){
		System.err.println(price1);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.between("price", price1, price2);
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		/*
		List<Hotel> hotels = new ArrayList<>();
		for(Standard s: standards) {
			QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hid", s.getHid());
			List<Hotel> tmp = hotelService.list(queryWrapper);
			hotels.addAll(tmp);
		}*/
		
		for(Room r : standards) {
			r.setPriceInt(Integer.valueOf(r.getPrice().replace("元", "").replace(",", "")));
		}
		return standards;
	}
	
	/**
	 * 根据price介于price1和price2返回hotel
	 * @param price1
	 * @param price2
	 * @return
	 * 
	 * 05-11fixed
	 */
	@GetMapping(path = "/getHotelByPrice")
	public List<Hotel> getHotelByPrice(@RequestParam("price1") String price1, @RequestParam("price2") String price2){
		System.err.println(price1);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.between("price", price1, price2);
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		
		List<Hotel> hotels = new ArrayList<>();
		for(Room s: standards) {
			QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hid", s.getHid());
			List<Hotel> tmp = hotelService.list(queryWrapper);
			hotels.addAll(tmp);
		}
		
		return hotels;
	}
	
	/**
	 * 
	 * @param des
	 * @param peopleNum1
	 * @param peopleNum2
	 * @param price1
	 * @param price2
	 * @return
	 */
	@GetMapping(path = "/getByAll")
	public List<Room> getByAll(@RequestParam("des") String des,
			@RequestParam("peopleNum1") String peopleNum1, @RequestParam("peopleNum2") String peopleNum2,
			@RequestParam("price1") String price1, @RequestParam("price2") String price2){
		System.err.println(price1);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.like("des", des).between("peopleNum", peopleNum1, peopleNum2).between("price", price1, price2);
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		/*
		List<Hotel> hotels = new ArrayList<>();
		for(Standard s: standards) {
			QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hid", s.getHid());
			List<Hotel> tmp = hotelService.list(queryWrapper);
			hotels.addAll(tmp);
		}*/
		
		for(Room r : standards) {
			r.setPriceInt(Integer.valueOf(r.getPrice().replace("元", "").replace(",", "")));
		}
		return standards;
	}
	
	/**
	 * 返回酒店
	 * @param des
	 * @param peopleNum1
	 * @param peopleNum2
	 * @param price1
	 * @param price2
	 * @return
	 */
	@GetMapping(path = "/getHotelByAll")
	public List<Hotel> getHotelByAll(@RequestParam("des") String des,
			@RequestParam("peopleNum1") String peopleNum1, @RequestParam("peopleNum2") String peopleNum2,
			@RequestParam("price1") String price1, @RequestParam("price2") String price2){
		System.err.println(price1);
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.like("des", des).between("peopleNum", peopleNum1, peopleNum2).between("price", price1, price2);
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		
		List<Hotel> hotels = new ArrayList<>();
		for(Room s: standards) {
			QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hid", s.getHid());
			List<Hotel> tmp = hotelService.list(queryWrapper);
			hotels.addAll(tmp);
		}
		
		return hotels;
	}
	
	/**
	 * 
	 * @param hid
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@PostMapping(path = "/getByDate")
	public List<Room> getByDate(@RequestBody Ord order){
		System.err.println(order.getSid());
		QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("hid", order.getSid());
		List<Room> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}
		
		//standards.sort((t1, t2) -> t2.getSid().compareTo(t1.getSid()));
		
		// check order
		
		/*
		List<Hotel> hotels = new ArrayList<>();
		for(Standard s: standards) {
			QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hid", s.getHid());
			List<Hotel> tmp = hotelService.list(queryWrapper);
			hotels.addAll(tmp);
		}*/
		
		Iterator<Room> it = standards.iterator();
		
		while(it.hasNext()) {
			Room r = (Room) it.next();
			int former = r.getTotalNum();
			
			r.setPriceInt(Integer.valueOf(r.getPrice().replace("元", "").replace(",", "")));
			
			QueryWrapper<Ord> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("sid", r.getSid());
			queryWrapper2.and(qwt->qwt.between("startTime", order.getStartTime(), order.getEndTime()));
			queryWrapper2.or(qwt->qwt.eq("sid", r.getSid()).le("startTime", order.getStartTime()))
					.and(w->w.between("endTime", order.getStartTime(), order.getEndTime()).or(q->q.ge("endTime", order.getEndTime())));
			
			
			List<Ord> orders = ordService.list(queryWrapper2);
			
			if(orders != null) {
				for(Ord o : orders) {
					System.err.println(former);
					former -= o.getNum();
				}
				if(former <= 0) {
					System.err.println(former);
					it.remove();
				}else {
					r.setTotalNum(former);
				}
			}
		}
		
		
		
		return standards;
	}
}

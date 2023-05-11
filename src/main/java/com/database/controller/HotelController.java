package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.*;
import com.database.service.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
@ResponseBody
public class HotelController {
	@Autowired
	private IHotelService hotelService;
	private IOrderService orderService;
	private IStandardService standardService;
	
	/**
	 * gethotel
	 * @param hid
	 * @return
	 */
	@GetMapping("/getHotelByHid")
	public Hotel getHotelByHid(@RequestParam("hid") Integer hid) {
		QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("hid", hid);
		Hotel hotel = hotelService.getOne(queryWrapper);
		if(hotel == null) {
			return null;
		}else {
			return hotel;
		}
	}
	
	/**
	 * searchhotel
	 * @param city,startDate,endDate,name
	 * @return
	 */
	@GetMapping("/searchHotel")
	public List<Hotel> searchHotel(@RequestParam(value="city",required=false) String city,@RequestParam(value="startDate",required=false) Date startDate,
			@RequestParam(value="endDate",required=false) Date endDate,@RequestParam(value="name",required=false) String name) {
		QueryWrapper<Hotel> queryHotel=new QueryWrapper<Hotel>();
		QueryWrapper<Order> queryOrder=new QueryWrapper<Order>();
		QueryWrapper<Standard> queryStandard=new QueryWrapper<Standard>();
		if(city!=null) {
			queryHotel.like("city", city);
		}
		if(name!=null) {
			queryHotel.like("name", name);
		}
		List<Hotel> hotels=hotelService.list(queryHotel);
		if(startDate!=null&&endDate!=null) {
			List<Integer> hidList=hotels.stream().map(Hotel::getHid).collect(Collectors.toList()); //hid列表
			Set<Integer> hidSet=new HashSet<Integer>(hidList); //hid集合
			queryStandard.in("hid", hidSet);
			List<Standard> standards=standardService.list(queryStandard);  
			List<Integer> sidList=standards.stream().map(Standard::getSid).collect(Collectors.toList()); //sid列表
			Set<Integer> sidSet=new HashSet<Integer>(sidList); //sid集合
			queryOrder.in("sid", sidSet);
			
			queryOrder.notBetween("startTime", startDate, endDate).notBetween("endTime", startDate, endDate);
			List<Order> orderList=orderService.list(queryOrder);
			List<Integer> sidList1=orderList.stream().map(Order::getSid).collect(Collectors.toList()); //筛选后的sid列表
			Set<Integer> sidSet1=new HashSet<Integer>(sidList1); //筛选后的sid集合
			
			queryStandard.in("sid", sidSet1);
			List<Standard> standards1=standardService.list(queryStandard);
			List<Integer> hidList1=standards1.stream().map(Standard::getHid).collect(Collectors.toList()); //筛选后的hid列表
			Set<Integer> hidSet1=new HashSet<Integer>(hidList1); //筛选后的hid集合
			
			queryHotel.in("hid", hidSet1);
			hotels=hotelService.list(queryHotel);
		}
		
		return hotels;
	}
}

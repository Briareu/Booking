package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	@Autowired
	private IOrdService orderService;
	@Autowired
	private IRoomService roomService;
	
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
	
	public class roomInfo {
	    private Integer sid;
	    private Integer hid;
	    private Integer totalNum;

	    public roomInfo(Integer sid, Integer hid,Integer totalNum) {
	        this.sid = sid;
	        this.hid=hid;
	        this.totalNum = totalNum;
	    }
	    
	    public Integer getSid() {
	    	return this.sid;
	    }
	    public Integer getTotalNum() {
	    	return this.totalNum;
	    }
	    public Integer getHid() {
	    	return this.hid;
	    }
	}
	
	/**
	 * searchhotel
	 * @param city,startDate,endDate,name
	 * @return
	 */
	@GetMapping("/searchHotel")
	public List<Hotel> searchHotel(@RequestParam(value="city",required=false) String city,@RequestParam(value="startDate",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value="endDate",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,@RequestParam(value="name",required=false) String name) {
		QueryWrapper<Hotel> queryHotel=new QueryWrapper<>();
			
		if(city!=null) {
			queryHotel.like("city", city);
		}
		if(name!=null) {
			queryHotel.like("name", name);
		}
		List<Hotel> hotels=hotelService.list(queryHotel);
		if(startDate!=null&&endDate!=null) {
			List<Hotel> hotels1=new ArrayList<Hotel>();
			List<Integer> hidList=hotels.stream().map(Hotel::getHid).collect(Collectors.toList()); //hid列表
			for(Integer hidi:hidList) {
				QueryWrapper<Room> queryRoom=new QueryWrapper<>();
				queryRoom.eq("hid", hidi);
				List<Room> roomList=roomService.list(queryRoom);
				List<roomInfo> sidList=roomList.stream().map(Room-> new roomInfo(Room.getSid(),Room.getHid(),Room.getTotalNum())).collect(Collectors.toList()); 
				for(roomInfo sidInfo:sidList) {
					QueryWrapper<Ord> queryOrder=new QueryWrapper<>();	
					queryOrder.eq("sid", sidInfo.getSid()).notBetween("startTime", startDate, endDate).notBetween("endTime",startDate, endDate);
					List<Ord> ordList=orderService.list(queryOrder);
					if(sidInfo.getTotalNum()-ordList.size()>0) {
						QueryWrapper<Hotel> queryHotel1=new QueryWrapper<>();
						queryHotel1.eq("hid",sidInfo.getHid());
						hotels1.add(hotelService.getOne(queryHotel1));
						break;
					}
				}
			}
			
			hotels=hotels1;
		}
		
		return hotels;
	}

	/**
	 * searchhotel
	 * @param hotelType
	 * @return
	 */
	@GetMapping("/getHotelByType")
	public List<Hotel> getHotelByType(@RequestParam("hotelType") String hotelType) {
		QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("leixing", hotelType);
		List<Hotel> hotels=hotelService.list(queryWrapper);
		return hotels;
	}
}

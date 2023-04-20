package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.Hotel;
import com.database.service.IHotelService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
@ResponseBody
public class HotelController {
	@Autowired
	private IHotelService hotelService;
	
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
}

package com.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.Standard;
import com.database.service.IStandardService;

/**
 * 放了一点
 * 前端控制器
 * @author RONG
 *
 */
@RestController
@RequestMapping(value = "/standard", produces = "text/html;charset=utf8")
@CrossOrigin
public class StandardController {
	@Autowired
	private IStandardService standardService;
	
	/**
	 * 获取hotel全部standard信息
	 * @param hid
	 * @return
	 */
	@GetMapping(value = "/getByHotel", produces = "text/html;charset=utf8")
	public List<Standard> getByHotel(@RequestParam("hid") Integer hid){
		System.err.println(hid);
		QueryWrapper<Standard> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("hid", hid);
		List<Standard> standards = standardService.list(queryWrapper1);
		
		if(standards == null) {
			return null;
		}else{
			return standards;
		}
	}
	
	/**
	 * 根据标准返回酒店
	 * @param description
	 * @return
	 */
	@GetMapping(path = "/getByDescription")
	public List<Standard> getByDescription(@RequestParam("des") String des){
		System.err.println(des);
		QueryWrapper<Standard> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("des", des);
		List<Standard> standards = standardService.list(queryWrapper1);
		if(standards == null) {
			return null;
		}
		return standards;
	}
	
	/**
	 * 根据人数介于paramNum1和paramNum2条件来返回standard
	 * @param peopleNum1
	 * @param peopleNum2
	 * @return
	 */
	@GetMapping(path = "/getByPeopelNum")
	public List<Standard> getByPeopleNum(@RequestParam("peopleNum") String peopleNum1, @RequestParam("peopleNum") String peopleNum2){
		System.err.println(peopleNum1);
		QueryWrapper<Standard> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.between("peopleNum", peopleNum1, peopleNum2);
		List<Standard> standards = standardService.list(queryWrapper1);
		
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
		return standards;
	}
	
	/**
	 * 根据price介于price1和price2返回standard
	 * @param price1
	 * @param price2
	 * @return
	 */
	@GetMapping(path = "/getByPrice")
	public List<Standard> getByPrice(@RequestParam("price") String price1, @RequestParam("price") String price2){
		System.err.println(price1);
		QueryWrapper<Standard> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.between("price", price1, price2);
		List<Standard> standards = standardService.list(queryWrapper1);
		
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
		
		return standards;
	}
}

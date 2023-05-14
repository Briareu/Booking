package com.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.Pic;
import com.database.service.IPicService;

/**
 * 只有一个方法
 * @author RONG
 * @since 4/12/2023
 * 
 * 05-11fixed
 * tested 5-12 under new pom
 */
@RestController
@RequestMapping(value = "/pic", produces = {"text/html;charset=utf8", "application/json;charset=utf8"})
@CrossOrigin
public class PicController {
	
	@Value("${file-upload-path}")
	private String pic_url;
	
	@Autowired
	private IPicService picService;
	
	/**
	 * 根据hid返回图片路径
	 * @param hid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getPicByHotel")
	public List<Pic> getPicByHotel(@RequestParam("hid") Integer hid) {
		QueryWrapper<Pic> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("hid", hid);
		List<Pic> pics = picService.list(queryWrapper);
		return pics;
	}
}

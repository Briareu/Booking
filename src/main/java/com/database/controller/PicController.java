package com.database.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.database.entity.Pic;
import com.database.mapper.PicMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 只有一个方法
 * @author RONG
 * @since 4/12/2023
 */
@RestController
@RequestMapping("/pic")
@CrossOrigin
public class PicController {
	
	@Value("${file-upload-path}")
	private String pic_url;
	
	private PicMapper picMapper;
	
	private ObjectMapper mapper;
	
	/**
	 * 根据hid返回图片路径
	 * @param hid
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getImgPathByHotel")
	public String getImgPathByHotel(@RequestParam("hid") Integer hid) {
		List<Pic> pics = picMapper.getPicByHotel(hid);
		HashMap<String, List> map = new HashMap<>();
		ArrayList<String> paths = new ArrayList<>();
		if(pics != null && !pics.isEmpty()) {
			for(Pic p : pics) {
				paths.add(pic_url + p.getPicUrl());
			}
		}
		map.put("paths", paths);
		
		String result;
		try {
			result = mapper.writeValueAsString(map);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
			return e.toString();
		}
		
		return result;
	}
}

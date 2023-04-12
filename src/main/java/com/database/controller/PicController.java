package com.database.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.database.entity.Pic;
import com.database.mapper.PicMapper;
import com.database.service.IPicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/pic")
@CrossOrigin
public class PicController {

	@Autowired
	private IPicService picService;
	
	@Value("${file-upload-path}")
	private String pic_url;
	
	private PicMapper picMapper;
	
	private ObjectMapper mapper;
	
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

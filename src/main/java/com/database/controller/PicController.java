package com.database.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.database.entity.Pic;
import com.database.service.IPicService;

import cn.hutool.core.lang.UUID;

@RestController
@RequestMapping("/pic")
@CrossOrigin
public class PicController {

	@Autowired
	private IPicService picService;
	
	@Value("${file-upload-path}")
	private String pic_url;
	
	@PostMapping(path = "/savePic")
	public String savePic(@RequestParam(value = "file") MultipartFile file, @RequestParam("reId") Integer hid) {
		String fileName  = file.getOriginalFilename();
		File saveFile = new File(pic_url);
		if(!saveFile.exists()) {
			saveFile.mkdir();
		}
		
		Pic pic = new Pic();
		
		//拼接url
		UUID uuid = UUID.randomUUID();
		
		//拼接后的url
		String url = file.getOriginalFilename().replace(".", "") + uuid;
		
		//图片名称
		String name = file.getOriginalFilename();
		
		pic.setPicUrl(url);
		pic.setPicName(name);
		pic.setHid(hid);
		
		picService.save(pic);
		try {
			file.
		}
	}
}

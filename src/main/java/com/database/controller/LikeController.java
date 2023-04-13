package com.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.database.entity.Like;
import com.database.service.ILikeService;

/**
 * 基本的增删改查
 * @author RONG
 *
 */
@RestController
@RequestMapping("/like")
@CrossOrigin
@ResponseBody
public class LikeController {

	@Autowired
	private ILikeService likeService;
	
	/**
	 * 根据用户返回收藏信息
	 * @return
	 */
	@GetMapping("/getLikeByUid")
	public List<Like> getLikeByUid(){
		List<Like> likes = likeService.list();
		return likes;
	}
	
	/**
	 * 添加like
	 * @param like
	 * @return
	 */
	@PostMapping("/addLike")
	public String addLike(@RequestBody Like like) {
		likeService.save(like);
		return "添加成功";
	}
	
	/**
	 * 根据lid更新
	 * @param like
	 * @return
	 */
	@PostMapping("/changeLike")
	public String changeLike(@RequestBody Like like) {
		QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("lid", like.getLid());
		if(likeService.getOne(queryWrapper) == null) {
			return "nolike";
		}else {
			Like thelike = likeService.getOne(queryWrapper);
			thelike.setHid(like.getHid());
			thelike.setUid(like.getUid());
			return "success";
		}
	}
	
	/**
	 * 根据id删除like
	 * @param lid
	 * @return
	 */
	@GetMapping("/delLike")
	public boolean delLike(@RequestParam("lid") Integer lid) {
		QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("lid", lid);
		boolean del = likeService.remove(queryWrapper);
		
		return del;
	}
}

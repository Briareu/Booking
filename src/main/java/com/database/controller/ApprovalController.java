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
import com.database.entity.Approval;
import com.database.service.IApprovalService;

/**
 * 基本的增删改查
 * @author RONG
 *
 */
@RestController
@RequestMapping("/like")
@CrossOrigin
@ResponseBody
public class ApprovalController {

	@Autowired
	private IApprovalService likeService;
	
	/**
	 * 根据用户返回收藏信息
	 * @return
	 */
	@GetMapping("/getLikeByUid")
	public List<Approval> getLikeByUid(@RequestParam("uid") Integer uid){
		QueryWrapper<Approval> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("uid", uid);
		List<Approval> likes = likeService.list(queryWrapper);
		return likes;
	}
	
	/**
	 * 添加like
	 * @param like
	 * @return
	 */
	@PostMapping("/addLike")
	public String addLike(@RequestBody Approval like) {
		Approval tmp = new Approval();
		tmp.setHid(like.getHid());
		tmp.setUid(like.getUid());
		likeService.save(tmp);
		return "添加成功";
	}
	
	/**
	 * 根据lid更新
	 * @param like
	 * @return
	 */
	@PostMapping("/changeLike")
	public String changeLike(@RequestBody Approval like) {
		QueryWrapper<Approval> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("lid", like.getLid());
		if(likeService.getOne(queryWrapper) == null) {
			return "nolike";
		}else {
			Approval thelike = likeService.getOne(queryWrapper);
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
		QueryWrapper<Approval> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("lid", lid);
		boolean del = likeService.remove(queryWrapper);
		
		return del;
	}
}

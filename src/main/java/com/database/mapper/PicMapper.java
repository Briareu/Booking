package com.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.database.entity.Pic;

public interface PicMapper extends BaseMapper<Pic> {
	@Select("select pid, picUrl, hid from pic where hid = #{hid}")
    public List<Pic> getPicByHotel(Integer hid);
}

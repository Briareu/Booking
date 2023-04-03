package com.database.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hotel")
public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "hid", type = IdType.AUTO)
	private Integer hid;
	
	@TableField("address")
	private String address;
	
	@TableField("province")
	private String province;
	
	@TableField("city")
	private String city;
	
	@TableField("region")
	private String region;
	
	@TableField("info")
	private String info;
	
	@TableField("star")
	private Integer star;
	
	@TableField("type")
	private String type;
	
	@TableField("comment")
	private String comment;
	
	@TableField("hName")
	private String hName;
	
	@TableField("score")
	private Double score;
}

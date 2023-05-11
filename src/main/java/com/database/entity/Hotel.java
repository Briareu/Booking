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
	
	@TableField("city")
	private String city;
	
	@TableField("info")
	private String info;
	
	@TableField("star")
	private Integer star;
	
	@TableField("type")
	private String type;
	
	@TableField("comment")
	private String comment;
	
	@TableField("name")
	private String name;
	
	@TableField("score")
	private Double score;
	
	@TableField("facility")
	private String facility;
}

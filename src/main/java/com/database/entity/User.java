package com.database.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户实体
 * 使用lombok
 * @author RONG
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "uid", type = IdType.AUTO)
	private Integer uid;
	
	@TableField("pwd")
	private String pwd;
	
	@TableField("name")
	private String name;
	
	@TableField("gender")
	private Integer gender;
	
	@TableField("mail")
	private String mail;
	
	@TableField("phone")
	private Integer phone;
	
	@TableField("state")
	private Integer state;
}

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
 * 图片（for酒店）实体类
 * 运用lombok
 * @author RONG
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pic")
public class Pic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "pid", type = IdType.AUTO)
	private Integer pid;
	
	@TableField("picUrl")
	private String picUrl;
	
	@TableField("picName")
	private String picName;
	
	@TableField("hid")
	private Integer hid;
}

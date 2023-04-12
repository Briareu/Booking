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
 * 标准实体类
 * 使用lombok
 * @author RONG
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("standard")
public class Standard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "sid", type = IdType.AUTO)
	private Integer sid;
	
	@TableId(value = "typeName")
	private String typeName;
	
	@TableField("description")
	private String description;
	
	@TableField("hid")
	private Integer hid;
	
	@TableField("totalNum")
	private Integer totalNum;
	
	@TableField("price")
	private String price;
	
	@TableField("peopleNum")
	private Integer peopleNum;
}

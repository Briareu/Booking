package com.database.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 预定实体类
 * lombok
 * mybatisplus
 * automatically generate getters and setters
 * @author RONG
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "oid", type = IdType.AUTO)
	private Integer oid;
	
	/**
	 * 总价，可能存在小数位——double
	 */
	@TableField("totalPrice")
	private Double totalPrice;
	
	@TableField("uid")
	private Integer uid;

	/**
	 * 创建时间：有数据库自动insert
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value = "createTime", fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	
	@TableField("state")
	private String state;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("startTime")
	private LocalDateTime startTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("endTime")
	private LocalDateTime endTime;
	
	@TableField("sid")
	private Integer sid;
}
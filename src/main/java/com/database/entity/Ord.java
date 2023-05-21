package com.database.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer;

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
@TableName("ord")
public class Ord implements Serializable {

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value = "createTime")
	private LocalDateTime createTime;
	
	@TableField("state")
	private String state;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value = "startTime")
	private LocalDateTime startTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("endTime")
	private LocalDateTime endTime;
	
	@TableField("sid")
	private Integer sid;
	
	@TableField("num")
	private Integer num;
	
	@TableField("ordseq")
	private String ordseq;
}

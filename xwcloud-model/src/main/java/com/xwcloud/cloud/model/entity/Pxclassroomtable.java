package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-24
 */
@Data
@Accessors(chain = true)
@TableName("pxclassroomtable")
public class Pxclassroomtable extends Model<Pxclassroomtable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	@TableField("classRoomName")
	private String classroomname;
	@TableField("campusID")
	private Long campusID;
	@TableField("recordInStaffID")
	private Long recordinstaffid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("recordInTime")
	private Date recordintime;
	@TableField("qiyeID")
	private Long qiyeID;
	@TableField("ischongtu")
	private Boolean ischongtu;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}


}

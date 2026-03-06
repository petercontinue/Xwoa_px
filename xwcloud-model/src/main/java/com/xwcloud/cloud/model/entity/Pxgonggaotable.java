package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-22
 */
@Data
@Accessors(chain = true)
@TableName("pxgonggaotable")
public class Pxgonggaotable extends Model<Pxgonggaotable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("gonggaoTitel")
	private String gonggaotitel;
	@TableField("gonggaoContent")
	private String gonggaocontent;
	@TableField("staffID")
	private Long staffid;
	@TableField("gonggaoDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gonggaodate;
	@TableField("fujian")
	private String fujian;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxgonggaotable{" +
			", id=" + id +
			", gonggaotitel=" + gonggaotitel +
			", gonggaocontent=" + gonggaocontent +
			", staffid=" + staffid +
			", gonggaodate=" + gonggaodate +
			", fujian=" + fujian +
			", qiyeid=" + qiyeID +
			"}";
	}
}

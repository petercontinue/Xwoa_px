package com.xwcloud.cloud.model.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-25
 */
@Data
@Accessors(chain = true)
public class Pxworkweekrecordtable extends Model<Pxworkweekrecordtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("staffID")
	private Long staffID;
	@TableField("startDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@TableField("endDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
    /**
     * 本周工作总结
     */
	@TableField("thisWeekRecord")
	private String thisWeekRecord;
    /**
     * 下周工作计划
     */
	@TableField("nextWeekRecord")
	private String nextWeekRecord;
	@TableField("luruDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date luruDate;
	@TableField("imgsUrl")
	private String imgsUrl;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxworkweekrecordtable{" +
			", id=" + id +
			", staffID=" + staffID +
			", startDate=" + startDate +
			", endDate=" + endDate +
			", thisWeekRecord=" + thisWeekRecord +
			", nextWeekRecord=" + nextWeekRecord +
			", luruDate=" + luruDate +
			", imgsUrl=" + imgsUrl +
			", qiyeID=" + qiyeID +
			"}";
	}
}

package com.xwcloud.cloud.model.log;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("logxjbtable")
public class Logxjbtable extends Model<Logxjbtable> {



	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	/**
	 * 日志内容，给系统使用者看的
	 */
	@TableField("systemContent")
	private String systemContent;
	/**
	 * 功能板块的名称
	 */
	@TableField("funcName")
	private String funcName;
	/**
	 * 员工操作产生的日志，填写上员工ID
	 */
	@TableField("staffID")
	private Long staffID;
	/**
	 * 员工姓名
	 */
	@TableField("staffName")
	private String staffName;
	/**
	 * 学员或学员家长操作产生的日志，填写学员ID
	 */
	@TableField("stuID")
	private Long stuID;
	/**
	 * 学生姓名
	 */
	@TableField("stuName")
	private String stuName;
	/**
	 * 员工日志1，学员日志2，系统自动产生的日志3
	 */
	@TableField("logType")
	private Integer logType;
	/**
	 * 日志时间
	 */
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;

	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Logxjbtable{" +
				", id=" + id +
				", systemContent=" + systemContent +
				", funcName=" + funcName +
				", staffID=" + staffID +
				", staffName=" + staffName +
				", stuID=" + stuID +
				", stuName=" + stuName +
				", logType=" + logType +
				", addTime=" + addTime +
				", qiyeID=" + qiyeID +
				"}";
	}


}

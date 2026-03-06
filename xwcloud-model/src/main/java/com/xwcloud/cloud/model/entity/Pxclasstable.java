package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-04-15
 */
@Data
@Accessors(chain = true)
@TableName("pxclasstable")
public class Pxclasstable extends Model<Pxclasstable> {

    private static final long serialVersionUID = 1L;

    /**
     * 班级编号classID
     */
    @TableId("id")
	private Long id;
    /**
     * 自定义班级编号
     */
	@TableField("zidingyiClassID")
	private String zidingyiClassID;
    /**
     * 班级名称
     */
	@TableField("className")
	private String className;
    /**
     * 校区ID
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 最大人数
     */
	@TableField("maxStuNum")
	private Integer maxStuNum;
    /**
     * 是否是1对1班级，0. 班课  1. 一对一
     */
	@TableField("is1v1Class")
	private Integer is1v1Class;
    /**
     * true:逻辑删除 false：未删除
     */
	@TableField("isdelete")
	private Boolean isdelete;
    /**
     * 是否启用，1启用，0不启用
     */
	@TableField("isShow")
	private Integer isShow;
	@TableField("addStaffID")
	private Long addStaffID;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("addTime")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 班级状态：0上课班级,1结课班级  
     */
	@TableField("classState")
	private Integer classState;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxclasstable{" +
			", id=" + id +
			", zidingyiClassID=" + zidingyiClassID +
			", className=" + className +
			", campusID=" + campusID +
			", maxStuNum=" + maxStuNum +
			", is1v1Class=" + is1v1Class +
			", isdelete=" + isdelete +
			", isShow=" + isShow +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			", classState=" + classState +
			"}";
	}
}

package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Data
@Accessors(chain = true)
public class Pxroomchecktable extends Model<Pxroomchecktable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 宿舍ID
     */
	@TableField("RoomID")
	private String RoomID;
    /**
     *  学员ID
     */
	@TableField("stuID")
	private String stuID;
    /**
     * 查寝室时学员的状态，1正常，2不在
     */
	@TableField("stuState")
	private Integer stuState;
    /**
     * 备注说明
     */
	@TableField("beizhu")
	private String beizhu;
    /**
     * 查寝时间
     */
	@TableField("checkTime")
	private Date checkTime;
    /**
     * 查寝老师，即录入人
     */
	@TableField("addStaffID")
	private String addStaffID;
    /**
     * 录入时间
     */
	@TableField("addTime")
	private Date addTime;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxroomchecktable{" +
			", id=" + id +
			", RoomID=" + RoomID +
			", stuID=" + stuID +
			", stuState=" + stuState +
			", beizhu=" + beizhu +
			", checkTime=" + checkTime +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}

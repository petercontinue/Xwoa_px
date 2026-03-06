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
public class Pxyueketeacherfabustutable extends Model<Pxyueketeacherfabustutable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 如果是微商城报名的，就可以填入本字段
     */
	@TableField("wxUserID")
	private String wxUserID;
    /**
     * 学员学号，如果是还没报名的学员，本字段可空着
     */
	@TableField("stuID")
	private String stuID;
    /**
     * 学员姓名
     */
	@TableField("stuName")
	private String stuName;
    /**
     * 学员手机号
     */
	@TableField("telphone")
	private String telphone;
    /**
     * 学员的补习课程ID
     */
	@TableField("buxiID")
	private String buxiID;
    /**
     * 老师发布的约课ID
     */
	@TableField("yuekeTeachFabuID")
	private Integer yuekeTeachFabuID;
    /**
     * 响应约课的时间
     */
	@TableField("addTime")
	private Date addTime;
    /**
     * 备注
     */
	@TableField("beizhu")
	private String beizhu;
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
		return "Pxyueketeacherfabustutable{" +
			", id=" + id +
			", wxUserID=" + wxUserID +
			", stuID=" + stuID +
			", stuName=" + stuName +
			", telphone=" + telphone +
			", buxiID=" + buxiID +
			", yuekeTeachFabuID=" + yuekeTeachFabuID +
			", addTime=" + addTime +
			", beizhu=" + beizhu +
			", qiyeID=" + qiyeID +
			"}";
	}
}

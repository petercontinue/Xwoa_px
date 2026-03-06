package com.xwcloud.cloud.model.entity;

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
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
@TableName("pxyxinvitationtable")
public class Pxyxinvitationtable extends Model<Pxyxinvitationtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("stuID")
	private Long stuID;
	@TableField("invitationTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date invitationTime;
	@TableField("invitationZhuangtai")
	private String invitationZhuangtai;
	@TableField("addTeacher")
	private Long addTeacher;
	@TableField("addTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	@TableField("shuoMing")
	private String shuoMing;
	@TableField("qiyeID")
	private Long qiyeID;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxyxinvitationtable{" +
			", id=" + id +
			", stuID=" + stuID +
			", invitationTime=" + invitationTime +
			", invitationZhuangtai=" + invitationZhuangtai +
			", addTeacher=" + addTeacher +
			", addTime=" + addTime +
			", shuoMing=" + shuoMing +
			", qiyeID=" + qiyeID +
			"}";
	}
}

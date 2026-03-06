package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Data
@Accessors(chain = true)
public class Pxwxusertable extends Model<Pxwxusertable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("openid")
	private String openid;
	@TableField("unidid")
	private String unidid;
	@TableField("tel")
	private String tel;
	@TableField("nickName")
	private String nickName;
	@TableField("headImage")
	private String headImage;
	@TableField("sex")
	private String sex;
	@TableField("diqu")
	private String diqu;
	@TableField("staffID")
	private String staffID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxwxusertable{" +
			", id=" + id +
			", openid=" + openid +
			", unidid=" + unidid +
			", tel=" + tel +
			", nickName=" + nickName +
			", headImage=" + headImage +
			", sex=" + sex +
			", diqu=" + diqu +
			", staffID=" + staffID +
			", qiyeID=" + qiyeID +
			"}";
	}
}

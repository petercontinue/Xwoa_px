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
 * @since 2020-12-02
 */
@Data
@Accessors(chain = true)
public class Pxoldschoolteachertable extends Model<Pxoldschoolteachertable> {

    private static final long serialVersionUID = 1L;

    @TableId("oldSchoolTeacherID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long oldSchoolTeacherID;
	@TableField("oldSchoolTeacherName")
	private String oldSchoolTeacherName;
	@TableField("oldSchoolTeacherTel")
	private String oldSchoolTeacherTel;
	@TableField("oldSchoolID")
	private Long oldSchoolID;

	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.oldSchoolTeacherID;
	}

	@Override
	public String toString() {
		return "Pxoldschoolteachertable{" +
			", oldSchoolTeacherID=" + oldSchoolTeacherID +
			", oldSchoolTeacherName=" + oldSchoolTeacherName +
			", oldSchoolTeacherTel=" + oldSchoolTeacherTel +
			", oldSchoolID=" + oldSchoolID +
			"}";
	}
}

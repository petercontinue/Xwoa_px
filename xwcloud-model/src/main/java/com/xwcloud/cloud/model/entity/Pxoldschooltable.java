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
 * @since 2020-12-01
 */
@Data
@Accessors(chain = true)
public class Pxoldschooltable extends Model<Pxoldschooltable> {

    private static final long serialVersionUID = 1L;

    /**
     * 原就读学校ID
     */
    @TableId("oldSchoolID")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long oldSchoolID;
    /**
     * 原就读学校名称
     */
	@TableField("oldSchoolName")
	private String oldSchoolName;

	@TableField("qiyeID")
	private Long qiyeID;



	@Override
	protected Serializable pkVal() {
		return this.oldSchoolID;
	}

	@Override
	public String toString() {
		return "Pxoldschooltable{" +
			", oldSchoolID=" + oldSchoolID +
			", oldSchoolName=" + oldSchoolName +
			"}";
	}
}

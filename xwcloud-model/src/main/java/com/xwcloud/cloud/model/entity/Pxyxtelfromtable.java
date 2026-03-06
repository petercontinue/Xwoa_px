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
 * @since 2020-12-01
 */
@Data
@Accessors(chain = true)
public class Pxyxtelfromtable extends Model<Pxyxtelfromtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 学生来源
     */
	@TableField("telFromName")
	private String telFromName;
	@TableField("beizhu")
	private String beizhu;
	@TableField("addStaffID")
	private Long addStaffID;
	@TableField("addTime")
	private Date addTime;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxyxtelfromtable{" +
			", id=" + id +
			", telFromName=" + telFromName +
			", beizhu=" + beizhu +
			", addStaffID=" + addStaffID +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}

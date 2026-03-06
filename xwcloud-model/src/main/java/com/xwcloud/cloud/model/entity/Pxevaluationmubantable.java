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
public class Pxevaluationmubantable extends Model<Pxevaluationmubantable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 模板评论内容
     */
	@TableField("note")
	private String note;
    /**
     * 老师id,是指添加该模板的老师
     */
	@TableField("teacherid")
	private String teacherid;
    /**
     * 是否启用,1启用，0不启用，默认值1
     */
	@TableField("isUser")
	private Integer isUser;
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
		return "Pxevaluationmubantable{" +
			", id=" + id +
			", note=" + note +
			", teacherid=" + teacherid +
			", isUser=" + isUser +
			", addTime=" + addTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}

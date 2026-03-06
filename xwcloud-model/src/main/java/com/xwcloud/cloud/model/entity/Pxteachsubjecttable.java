package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-01
 */
@Data
@Accessors(chain = true)
@TableName("pxteachsubjecttable")
public class Pxteachsubjecttable extends Model<Pxteachsubjecttable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("staffID")
	private Long staffID;
    /**
     * 教学科目ID
     */
	@TableField("teachSubjectID")
	private Long teachSubjectID;
	@TableField("shuoming")
	private String shuoming;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxteachsubjecttable{" +
			", id=" + id +
			", staffID=" + staffID +
			", teachSubjectID=" + teachSubjectID +
			", shuoming=" + shuoming +
			", qiyeID=" + qiyeID +
			"}";
	}
}

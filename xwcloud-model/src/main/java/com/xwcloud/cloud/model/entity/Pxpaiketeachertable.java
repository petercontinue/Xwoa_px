package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("pxpaiketeachertable")
public class Pxpaiketeachertable extends Model<Pxpaiketeachertable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("paikeID")
	private Long paikeID;
	@TableField("teacherID")
	private Long teacherID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxpaiketeachertable{" +
			", id=" + id +
			", paikeID=" + paikeID +
			", teacherID=" + teacherID +
			", qiyeID=" + qiyeID +
			"}";
	}
}

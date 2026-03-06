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
 * @since 2020-11-23
 */
@Data
@Accessors(chain = true)
@TableName("pxkeshistuteachertable")
public class Pxkeshistuteachertable extends Model<Pxkeshistuteachertable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	@TableField("keshiStuTableID")
	private Long keshistutableid;
	@TableField("teacherID")
	private Long teacherid;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxkeshistuteachertable{" +
				", id=" + id +
				", keshistutableid=" + keshistutableid +
				", teacherid=" + teacherid +
				", qiyeid=" + qiyeID +
				"}";
	}
}

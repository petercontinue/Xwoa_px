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
@TableName("pxstuclasstable")
public class Pxstuclasstable extends Model<Pxstuclasstable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 关联pxbuxikechengTable里ID
     */
	@TableField("buxiID")
	private Long buxiID;
    /**
     * 班级ID
     */
	@TableField("classID")
	private Long classID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxstuclasstable{" +
			", id=" + id +
			", buxiID=" + buxiID +
			", classID=" + classID +
			", qiyeID=" + qiyeID +
			"}";
	}
}

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
 * @since 2021-06-27
 */
@Data
@Accessors(chain = true)
@TableName("pxbuxistyletable")
public class Pxbuxistyletable extends Model<Pxbuxistyletable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 培训方式，一对一的培训方式不允许删除，也不允许修改
     */
	@TableField("buxiStyleName")
	private String buxiStyleName;
    /**
     * 是不是一对一。0不是，1是，默认值0
     */
	@TableField("is1v1")
	private Integer is1v1;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxbuxistyletable{" +
			", id=" + id +
			", buxiStyleName=" + buxiStyleName +
			", is1v1=" + is1v1 +
			", qiyeID=" + qiyeID +
			"}";
	}
}

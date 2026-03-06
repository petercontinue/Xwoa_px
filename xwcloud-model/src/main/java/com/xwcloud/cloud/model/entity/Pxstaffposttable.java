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
 * @since 2021-06-10
 */
@Data
@Accessors(chain = true)
@TableName("pxstaffposttable")
public class Pxstaffposttable extends Model<Pxstaffposttable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 岗位名称
     */
	@TableField("staffpostName")
	private String staffpostName;
    /**
     * 校区ID
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxstaffposttable{" +
			", id=" + id +
			", staffpostName=" + staffpostName +
			", campusID=" + campusID +
			", qiyeID=" + qiyeID +
			"}";
	}
}

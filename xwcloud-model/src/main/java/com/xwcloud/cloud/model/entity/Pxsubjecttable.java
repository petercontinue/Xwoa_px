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
 * @since 2020-11-25
 */
@Data
@Accessors(chain = true)
@TableName("pxsubjecttable")
public class Pxsubjecttable extends Model<Pxsubjecttable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	@TableField("subjectName")
	private String subjectName;
	@TableField("campusID")
	private Long campusID;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}


}

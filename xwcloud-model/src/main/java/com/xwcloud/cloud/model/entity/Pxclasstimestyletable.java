package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-20
 */
@Data
@Accessors(chain = true)
@TableName("pxclasstimestyletable")
public class Pxclasstimestyletable extends Model<Pxclasstimestyletable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	/**
	 * 每个课时多少分钟;  -1表示1次，-2表示1天
	 */
	@TableField("classTimeStyleName")
	private String classtimestylename;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}

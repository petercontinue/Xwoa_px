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
@TableName("pxstugradetable")
public class Pxstugradetable extends Model<Pxstugradetable> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;
	@TableField("stuGradeName")
	private String stugradename;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxstugradetable{" +
				", id=" + id +
				", stugradename=" + stugradename +
				", qiyeid=" + qiyeID +
				"}";
	}
}

package com.xwcloud.cloud.model.entity;

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
 * @since 2020-11-21
 */
@Data
@Accessors(chain = true)
public class Pxteachingsuppliestypetable extends Model<Pxteachingsuppliestypetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("typeName")
	private String typeName;
	@TableField("qiyeID")
	private Long qiyeID;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxteachingsuppliestypetable{" +
			", id=" + id +
			", typeName=" + typeName +
			", qiyeID=" + qiyeID +
			"}";
	}
}

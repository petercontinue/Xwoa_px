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
 * @since 2021-06-21
 */
@Data
@Accessors(chain = true)
@TableName("pxtuisongtypetable")
public class Pxtuisongtypetable extends Model<Pxtuisongtypetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("tuisongType")
	private String tuisongType;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxtuisongtypetable{" +
			", id=" + id +
			", tuisongType=" + tuisongType +
			"}";
	}
}

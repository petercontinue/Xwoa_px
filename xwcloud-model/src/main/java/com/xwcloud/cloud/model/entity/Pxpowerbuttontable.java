package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-07
 */
@Data
@Accessors(chain = true)
public class Pxpowerbuttontable extends Model<Pxpowerbuttontable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("buttonName")
	private String buttonName;
	@TableField("onclicks")
	private String onclicks;
	@TableField("icons")
	private String icons;
	@TableField("btnClass")
	private String btnClass;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxpowerbuttontable{" +
			", id=" + id +
			", buttonName=" + buttonName +
			", onclicks=" + onclicks +
			", icons=" + icons +
			", btnClass=" + btnClass +
			"}";
	}
}

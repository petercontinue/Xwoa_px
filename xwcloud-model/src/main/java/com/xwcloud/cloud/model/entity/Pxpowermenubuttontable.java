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
public class Pxpowermenubuttontable extends Model<Pxpowermenubuttontable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	@TableField("menuID")
	private Long menuID;
	@TableField("buttonID")
	private Long buttonID;
    /**
     * 是否显示，1显示，0不显示，默认值1
     */
	@TableField("isShow")
	private Integer isShow;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxpowermenubuttontable{" +
			", id=" + id +
			", menuID=" + menuID +
			", buttonID=" + buttonID +
			", isShow=" + isShow +
			"}";
	}
}

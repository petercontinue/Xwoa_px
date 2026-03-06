package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-30
 */
@Data
@Accessors(chain = true)
@TableName("pxdaohangtable")
public class Pxdaohangtable extends Model<Pxdaohangtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("menuID")
	private Long menuID;
	@TableField("text")
	private String text;
	@TableField("url")
	private String url;
	@TableField("iconClass")
	private String iconClass;
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
		return "Pxdaohangtable{" +
			", id=" + id +
			", menuID=" + menuID +
			", text=" + text +
			", url=" + url +
			", iconClass=" + iconClass +
			", qiyeID=" + qiyeID +
			"}";
	}
}

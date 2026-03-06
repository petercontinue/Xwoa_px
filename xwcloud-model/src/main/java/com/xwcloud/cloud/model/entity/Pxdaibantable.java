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
 * @since 2021-07-29
 */
@Data
@Accessors(chain = true)
@TableName("pxdaibantable")
public class Pxdaibantable extends Model<Pxdaibantable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private int id;
	@TableField("daibanItemName")
	private String daibanItemName;
	@TableField("daibanShuoming")
	private String daibanShuoming;
	@TableField("daibanTypeID")
	private String daibanTypeID;
    /**
     * 是否显示，1显示，2不显示，默认值1
     */
	@TableField("isShow")
	private Integer isShow;
	@TableField("menuID")
	private String menuID;
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
		return "Pxdaibantable{" +
			", id=" + id +
			", daibanItemName=" + daibanItemName +
			", daibanShuoming=" + daibanShuoming +
			", daibanTypeID=" + daibanTypeID +
			", isShow=" + isShow +
			", menuID=" + menuID +
			", qiyeID=" + qiyeID +
			"}";
	}
}

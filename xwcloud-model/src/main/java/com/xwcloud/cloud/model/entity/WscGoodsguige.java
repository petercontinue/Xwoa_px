package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>a
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Data
@Accessors(chain = true)
@TableName("wsc_goodsguige")
public class WscGoodsguige extends Model<WscGoodsguige> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("guigeTypeName")
	private String guigetypename;
	@TableField("goodsID")
	private Long goodsid;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscGoodsguige{" +
			", id=" + id +
			", guigetypename=" + guigetypename +
			", goodsid=" + goodsid +
			", qiyeid=" + qiyeID +
			"}";
	}
}

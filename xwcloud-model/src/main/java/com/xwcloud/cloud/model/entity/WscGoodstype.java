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
@TableName("wsc_goodstype")
public class WscGoodstype extends Model<WscGoodstype> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 商品类别名称
     */
	@TableField("goodsType")
	private String goodstype;
    /**
     * 是第几级类别，1表示一级类别，2表示二级类别
     */
	@TableField("typeLevel")
	private Integer typelevel;
    /**
     * 父级类别ID
     */
	@TableField("fid")
	private Long fid;
    /**
     * 是否启用（显示），1启用，0不启用，默认值1
     */
	@TableField("isShow")
	private Integer isshow;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WscGoodstype{" +
			", id=" + id +
			", goodstype=" + goodstype +
			", typelevel=" + typelevel +
			", fid=" + fid +
			", isshow=" + isshow +
			", qiyeid=" + qiyeID +
			"}";
	}
}

package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;
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
public class Pxteachingsuppliesorderdetailtable extends Model<Pxteachingsuppliesorderdetailtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 订单ID
     */
	@TableField("OrderID")
	private Long OrderID;
    /**
     * 物品ID
     */
	@TableField("SuppliesID")
	private Long SuppliesID;
    /**
     * 物品名称
     */
	@TableField("SuppliesName")
	private String SuppliesName;
    /**
     * 购买数量
     */
	@TableField("BuySum")
	private BigDecimal BuySum;
    /**
     * 购买单价
     */
	@TableField("BuyPrice")
	private BigDecimal BuyPrice;
    /**
     * 物品识别码
     */
	@TableField("SuppliesTiaoma")
	private String SuppliesTiaoma;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxteachingsuppliesorderdetailtable{" +
			", id=" + id +
			", OrderID=" + OrderID +
			", SuppliesID=" + SuppliesID +
			", SuppliesName=" + SuppliesName +
			", BuySum=" + BuySum +
			", BuyPrice=" + BuyPrice +
			", SuppliesTiaoma=" + SuppliesTiaoma +
			", qiyeID=" + qiyeID +
			"}";
	}
}

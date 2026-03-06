package com.xwcloud.cloud.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-14
 */
@Data
@Accessors(chain = true)
@TableName("pxteachingsuppliestable")
public class Pxteachingsuppliestable extends Model<Pxteachingsuppliestable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("campusID")
	private Long campusID;
    /**
     * 物品名称
     */
	@TableField("name")
	private String name;
    /**
     * 分类id
     */
	@TableField("typeId")
	private Long typeId;
    /**
     * 规格
     */
	@TableField("specs")
	private String specs;
    /**
     * 库存数量
     */
	@TableField("StockNum")
	private BigDecimal stockNum;
    /**
     * 数量单位
     */
	@TableField("StockUnit")
	private String stockUnit;
	@TableField("addDate")
	private Date addDate;
    /**
     * 入库验收人ID
     */
	@TableField("yanshouStaffId")
	private Long yanshouStaffId;
    /**
     * 入库说明
     */
	@TableField("rukuShuoming")
	private String rukuShuoming;
    /**
     * 采购价
     */
	@TableField("buyPrice")
	private BigDecimal buyPrice;
    /**
     * 销售价
     */
	@TableField("salePrice")
	private BigDecimal salePrice;
    /**
     * 低库存预警
     */
	@TableField("kucunyujing")
	private Integer kucunyujing;
    /**
     * 是否启用,true启用,false不启用
     */
	@TableField("IsQiYong")
	private Boolean isQiYong;
    /**
     * 物品条码
     */
	@TableField("changpinTiaoma")
	private String changpinTiaoma;

	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxteachingsuppliestable{" +
			", id=" + id +
			", campusID=" + campusID +
			", name=" + name +
			", typeId=" + typeId +
			", specs=" + specs +
			", stockNum=" + stockNum +
			", stockUnit=" + stockUnit +
			", addDate=" + addDate +
			", yanshouStaffId=" + yanshouStaffId +
			", rukuShuoming=" + rukuShuoming +
			", buyPrice=" + buyPrice +
			", salePrice=" + salePrice +
			", kucunyujing=" + kucunyujing +
			", isQiYong=" + isQiYong +
			", changpinTiaoma=" + changpinTiaoma +
			", qiyeID=" + qiyeID +
			"}";
	}
}

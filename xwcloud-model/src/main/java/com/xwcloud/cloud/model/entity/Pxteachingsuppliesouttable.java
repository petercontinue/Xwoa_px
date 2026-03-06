package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-05-31
 */
@Data
@Accessors(chain = true)
@TableName("pxteachingsuppliesouttable")
public class Pxteachingsuppliesouttable extends Model<Pxteachingsuppliesouttable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 物品ID
     */
	@TableField("suppliesId")
	private Long suppliesId;
    /**
     * 出库人ID
     */
	@TableField("outStaffId")
	private Long outStaffId;
    /**
     * 出库原因
     */
	@TableField("outReason")
	private String outReason;
    /**
     * 录入人
     */
	@TableField("luruStaffId")
	private Long luruStaffId;
    /**
     * 出库时间
     */
	@TableField("outDate")
	private Date outDate;
    /**
     * 出库数量
     */
	@TableField("outNum")
	private BigDecimal outNum;
    /**
     * 出库前的数量
     */
	@TableField("outNum_before")
	private BigDecimal outnumBefore;
    /**
     * 1.入库 2.出库
     */
	@TableField("type")
	private Integer type;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxteachingsuppliesouttable{" +
			", id=" + id +
			", suppliesId=" + suppliesId +
			", outStaffId=" + outStaffId +
			", outReason=" + outReason +
			", luruStaffId=" + luruStaffId +
			", outDate=" + outDate +
			", outNum=" + outNum +
			", outnumBefore=" + outnumBefore +
			", type=" + type +
			", qiyeID=" + qiyeID +
			"}";
	}
}

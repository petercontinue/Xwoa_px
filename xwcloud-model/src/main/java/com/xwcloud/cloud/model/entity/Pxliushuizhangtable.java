package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
public class Pxliushuizhangtable extends Model<Pxliushuizhangtable> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId("id")
	private Long id;
    /**
     * 流水时间
     */
	@TableField("liushuiDateTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date liushuiDateTime;
    /**
     * 校区ID
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 流水摘要
     */
	@TableField("liushuiZaiyao")
	private String liushuiZaiyao;
    /**
     * 付款方式ID
     */
	@TableField("payMoneyStyle")
	private Long payMoneyStyle;
    /**
     * 收入金额
     */
	@TableField("shouruMoney")
	private BigDecimal shouruMoney;
    /**
     * 支出金额
     */
	@TableField("zhichuMoney")
	private BigDecimal zhichuMoney;
    /**
     * 收支类别ID
     */
	@TableField("shouzhiStyleID")
	private Long shouzhiStyleID;
    /**
     * 经办人
     */
	@TableField("jinbanRen")
	private Long jinbanRen;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 签单ID
     */
	@TableField("qiandanID")
	private Long qiandanID;
    /**
     * 录入人,以前没这个字段
     */
	@TableField("addStaffID")
	private Long addStaffID;
    /**
     * 录入时间
     */
	@TableField("luruTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date luruTime;
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
		return "Pxliushuizhangtable{" +
			", id=" + id +
			", liushuiDateTime=" + liushuiDateTime +
			", campusID=" + campusID +
			", liushuiZaiyao=" + liushuiZaiyao +
			", payMoneyStyle=" + payMoneyStyle +
			", shouruMoney=" + shouruMoney +
			", zhichuMoney=" + zhichuMoney +
			", shouzhiStyleID=" + shouzhiStyleID +
			", jinbanRen=" + jinbanRen +
			", stuID=" + stuID +
			", qiandanID=" + qiandanID +
			", addStaffID=" + addStaffID +
			", luruTime=" + luruTime +
			", qiyeID=" + qiyeID +
			"}";
	}
}

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
public class Pxqiandaninfotable extends Model<Pxqiandaninfotable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
    /**
     * 学员ID
     */
	@TableField("stuID")
	private Long stuID;
    /**
     * 签单日期
     */
	@TableField("qiandandate")
	private Date qiandandate;
    /**
     * 实收金额
     */
	@TableField("shishouTotalMoney")
	private BigDecimal shishouTotalMoney;
    /**
     * 合同金额
     */
	@TableField("HetongMoney")
	private BigDecimal HetongMoney;
    /**
     * 家长要求
     */
	@TableField("jiazhangDemand")
	private String jiazhangDemand;
    /**
     * 转介绍ID,如果为空，即不是转介绍
     */
	@TableField("zhuanjieshaoID")
	private Long zhuanjieshaoID;
    /**
     * 1新签；2续费；3 退费 4 转送 5 换课换出 6 换课得到
     */
	@TableField("moneyStyle")
	private long moneyStyle;
	@TableField("beizhu")
	private String beizhu;
    /**
     * 签单人
     */
	@TableField("qianDanStaffID")
	private Long qianDanStaffID;
    /**
     * 录入人
     */
	@TableField("recordInStaffID")
	private Long recordInStaffID;
	@TableField("recordInTime")
	private Date recordInTime;
    /**
     * 支付方式，关联pxpaymoneystyletable表的ID
	 * 小程序支付-1，充值余额支付-2
     */
	@TableField("PayMoneyStyle")
	private Long PayMoneyStyle;
    /**
     * 转送ID，关联转送表的ID
     */
	@TableField("zhuansongID")
	private Long zhuansongID;
    /**
     * 校区ID
     */
	@TableField("campusID")
	private Long campusID;
    /**
     * 学生来源，关联pxtelfromtable的ID
     */
	@TableField("fromType")
	private Long fromType;
    /**
     * 1意向学员签单，2直接后台录入签单，3微信端支付自动录单
     */
	@TableField("qiandanType")
	private Integer qiandanType;
	@TableField("hetong")
	private String hetong;
    /**
     * 优惠ID
     */
	@TableField("youhuiID")
	private Long youhuiID;
    /**
     * 优惠金额
     */
	@TableField("youhuijine")
	private String youhuijine;
    /**
     * 优惠说明
     */
	@TableField("youhuishuoming")
	private String youhuishuoming;
    /**
     * 1:全款（没有补交过尾款）；2：全款（补交过尾款）；3：定金；4：定金（交过尾款）
     */
	@TableField("isdingjing")
	private Integer isdingjing;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxqiandaninfotable{" +
			", id=" + id +
			", stuID=" + stuID +
			", qiandandate=" + qiandandate +
			", shishouTotalMoney=" + shishouTotalMoney +
			", HetongMoney=" + HetongMoney +
			", jiazhangDemand=" + jiazhangDemand +
			", zhuanjieshaoID=" + zhuanjieshaoID +
			", moneyStyle=" + moneyStyle +
			", beizhu=" + beizhu +
			", qianDanStaffID=" + qianDanStaffID +
			", recordInStaffID=" + recordInStaffID +
			", recordInTime=" + recordInTime +
			", PayMoneyStyle=" + PayMoneyStyle +
			", zhuansongID=" + zhuansongID +
			", campusID=" + campusID +
			", fromType=" + fromType +
			", qiandanType=" + qiandanType +
			", hetong=" + hetong +
			", youhuiID=" + youhuiID +
			", youhuijine=" + youhuijine +
			", youhuishuoming=" + youhuishuoming +
			", isdingjing=" + isdingjing +
			", qiyeID=" + qiyeID +
			"}";
	}
}

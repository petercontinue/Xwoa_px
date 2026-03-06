package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-08-25
 */
@Data
@Accessors(chain = true)
@TableName("oa_qiandan_xufei_sp")
public class OaQiandanXufeiSp extends Model<OaQiandanXufeiSp> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;

	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 原有校区数
     */
	@TableField("oldCampusNum")
	private Integer oldCampusNum;
    /**
     * 加校区数量，默认值0；
     */
	@TableField("addCampusNum")
	private Integer addCampusNum;
    /**
     * 增加的校区的下次付款时间
     */
	@TableField("addCampusNextpayDateTime")
	private String addCampusNextpayDateTime;
    /**
     * 设置的下次付款时间；
     */
	@TableField("nextpayDateTime")
	private Date nextpayDateTime;
    /**
     * 原套餐ID
     */
	@TableField("oldTaocanTypeID")
	private Long oldTaocanTypeID;
    /**
     * 新套餐ID
     */
	@TableField("newTaocanTypeID")
	private Long newTaocanTypeID;
    /**
     * 硬件发货状态：0无硬件，不需要发货，1有硬件-未发货，2有硬件-已发货，默认0
     */
	@TableField("hardwareFahuoState")
	private Integer hardwareFahuoState;
    /**
     * 合伙人ID，0表示不是转介绍，默认值0
     */
	@TableField("hehuorenID")
	private Long hehuorenID;
    /**
     * 淘宝订单ID，如果不是淘宝也要说明付款方式
     */
	@TableField("orderid")
	private String orderid;
    /**
     * 软件金额
     */
	@TableField("ruanjianjine")
	private BigDecimal ruanjianjine;
    /**
     * 硬件金额，默认值0
     */
	@TableField("yingjianjine")
	private BigDecimal yingjianjine;
    /**
     * 代金券，默认值0
     */
	@TableField("daijinquan")
	private BigDecimal daijinquan;
    /**
     * 实收金额=软件金额+硬件金额-代金券
     */
	@TableField("shishoumoney")
	private BigDecimal shishoumoney;
    /**
     * 签单人
     */
	@TableField("salestaffID")
	private Long salestaffID;
    /**
     * 签单日期（客户付款日期）
     */
	@TableField("qiandanDatetime")
	private Date qiandanDatetime;
    /**
     * 续费类型：1续费，2加校区，3改套餐，4加校区且改套餐，默认值1
     */
	@TableField("xufeiType")
	private Integer xufeiType;
    /**
     * 最大学员人数，0表示不限制
     */
	@TableField("maxStuNum")
	private Integer maxStuNum;
    /**
     * 签单的赠送说明
     */
	@TableField("zengsong")
	private String zengsong;
    /**
     * 购买硬件说清单说明
     */
	@TableField("buyhardwarelists")
	private String buyhardwarelists;
    /**
     * 签单备注
     */
	@TableField("qiandanbeizhu")
	private String qiandanbeizhu;
	@TableField("addUser")
	private Long addUser;
	@TableField("addTime")
	private Date addTime;
    /**
     * 审批状态,1未审批，2已审批-未通过，3已审批通过，默认1
     */
	@TableField("shengpiState")
	private Integer shengpiState;
    /**
     * 审批-未通过原因
     */
	@TableField("shenpiNopassReason")
	private String shenpiNopassReason;
    /**
     * 审批时间
     */
	@TableField("shengpiDate")
	private Date shengpiDate;
    /**
     * 审批人
     */
	@TableField("shengpiStaff")
	private String shengpiStaff;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}

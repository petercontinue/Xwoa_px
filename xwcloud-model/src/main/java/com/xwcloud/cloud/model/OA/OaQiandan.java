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
 * @since 2021-08-23
 */
@Data
@Accessors(chain = true)
@TableName("oa_qiandan")
public class OaQiandan extends Model<OaQiandan> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 区域ID
     */
	@TableField("area")
	private Long area;
	@TableField("areaname")
	private String areaname;
    /**
     * 购买硬件说清单说明
     */
	@TableField("buyhardwarelists")
	private String buyhardwarelists;
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
     * 返拥金额
     */
	@TableField("fanyong")
	private BigDecimal fanyong;
    /**
     * 返佣状态，0没返，1返了，默认值0
     */
	@TableField("fanyongState")
	private Integer fanyongState;
    /**
     * 返佣操作人
     */
	@TableField("fanyongOptStaffID")
	private Long fanyongOptStaffID;
    /**
     * 返佣时间，即什么时间返佣的
     */
	@TableField("fanyongTime")
	private LocalDateTime fanyongTime;
    /**
     * 返佣的财务流水ID
     */
	@TableField("fanyongLiushuiID")
	private Long fanyongLiushuiID;
    /**
     * 返佣说明
     */
	@TableField("fanyongBeizhu")
	private String fanyongBeizhu;
	@TableField("hetong")
	private String hetong;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;
    /**
     * 淘宝订单ID，如果不是淘宝也要说明付款方式
     */
	@TableField("orderid")
	private String orderid;
    /**
     * 签单日期（客户付款日期）
     */
	@TableField("qiandanDatetime")
	private Date qiandanDatetime;
    /**
     * 下次付款日期
     */
	@TableField("nextpayDate")
	private Date nextpayDate;
    /**
     * 签单状态：1正常，2已退费，默认值1
     */
	@TableField("qiandanstate")
	private Integer qiandanstate;
    /**
     * 签单备注
     */
	@TableField("qiandanbeizhu")
	private String qiandanbeizhu;
    /**
     * 套餐类别
     */
	@TableField("taocanTypeID")
	private Long taocanTypeID;
    /**
     * 培训类别
     */
	@TableField("peixuntypeID")
	private Long peixuntypeID;
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
     * 实收金额
     */
	@TableField("shishoumoney")
	private BigDecimal shishoumoney;
    /**
     * 签单人
     */
	@TableField("salestaffID")
	private Long salestaffID;
    /**
     * 新签还是续费，1新签，2续费
     */
	@TableField("xinqianorxufei")
	private Integer xinqianorxufei;
    /**
     * 续费类型：1续费，2加校区，3改套餐，4加校区且改套餐，5买新产品，默认值1
     */
	@TableField("xufeiType")
	private Integer xufeiType;
    /**
     * 校区数，默认值0
     */
	@TableField("campusNum")
	private Integer campusNum;
    /**
     * 签单的赠送说明
     */
	@TableField("zengsong")
	private String zengsong;
    /**
     * 最大学员人数，0表示不限制
     */
	@TableField("maxStuNum")
	private Integer maxStuNum;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}

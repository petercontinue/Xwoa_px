package com.xwcloud.cloud.model.OA.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QiandanSpVo implements Serializable {

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
     * 软件金额
     */
    @TableField("ruanjianjine")
    private BigDecimal ruanjianjine;
    /**
     * 签单人
     */
    @TableField("salestaffID")
    private Long salestaffID;
    /**
     * 实收金额
     */
    @TableField("shishoumoney")
    private BigDecimal shishoumoney;
    /**
     * 套餐类别ID
     */
    @TableField("taocanTypeID")
    private Long taocanTypeID;
    /**
     * 新签还是续费，1新签，2续费
     */
    @TableField("xinqianorxufei")
    private Integer xinqianorxufei;
    /**
     * 硬件金额，默认值0
     */
    @TableField("yingjianjine")
    private BigDecimal yingjianjine;
    /**
     * 签单的赠送说明
     */
    @TableField("zengsong")
    private String zengsong;
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

    @TableField("xufeiType")
    private Integer xufeiType;

    @TableField("campusNum")
    private Integer campusNum;

    private String kehucompanyname;
    private String taocanName;
    private String staffName;

}

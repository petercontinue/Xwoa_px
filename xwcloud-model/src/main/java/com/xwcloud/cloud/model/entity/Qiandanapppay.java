package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

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
 * @since 2021-05-28
 */
@Data
@Accessors(chain = true)
@TableName("qiandanapppay")
public class Qiandanapppay extends Model<Qiandanapppay> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 签单日期
     */
    @TableField("qiandanDate")
    private Date qiandanDate;
    /**
     * 杂费金额
     */
    @TableField("zafeimoney")
    private BigDecimal zafeimoney;
    /**
     * 物品金额
     */
    @TableField("wupinmoney")
    private BigDecimal wupinmoney;
    /**
     * 课程总金额
     */
    @TableField("kechengmoney")
    private BigDecimal kechengmoney;
    /**
     * 实收金额
     */
    @TableField("shishouTotalMoney")
    private BigDecimal shishouTotalMoney;
    /**
     * 合同金额
     */
    @TableField("HetongMoney")
    private BigDecimal hetongMoney;
    /**
     * 定金金额
     */
    @TableField("dingjing")
    private BigDecimal dingjing;
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
    private Integer moneyStyle;
    /**
     * 签备注信息
     */
    @TableField("beizhu")
    private String beizhu;
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
    /**
     * 优惠ID
     */
    @TableField("youhuiID")
    private Long youhuiID;
    /**
     * 优惠金额
     */
    @TableField("youhuijine")
    private BigDecimal youhuijine;
    /**
     * 优惠说明
     */
    @TableField("youhuishuoming")
    private String youhuishuoming;
    /**
     * 代金券金额
     */
    @TableField("daijinquanmoney")
    private BigDecimal daijinquanmoney;
    /**
     * 提交小程序支付 员工
     */
    @TableField("addstaffID")
    private Long addstaffID;
    /**
     * 1:全款（没有补交过尾款）；2：全款（补交过尾款）；3：定金；4：定金（交过尾款）
     */
    @TableField("isdingjing")
    private Integer isdingjing;
    /**
     * 签单类型：1新签；2续费
     */
    @TableField("qdtype")
    private Integer qdtype;
    /**
     * 学员状态1意向，2在读，3停课，4结课，5退费，6休眠，7新签待审批
     */
    @TableField("buxiStateID")
    private Integer buxiStateID;
    /**
     * 1：待支付   2：已支付
     */
    @TableField("payState")
    private Integer payState;
    /**
     * 小程序支付时间
     */
    @TableField("payDate")
    private Date payDate;
    /**
     * 微商城支付人
     */
    @TableField("payUser")
    private Long payUser;
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
        return "Qiandanapppay{" +
                ", id=" + id +
                ", stuID=" + stuID +
                ", qiandanDate=" + qiandanDate +
                ", zafeimoney=" + zafeimoney +
                ", wupinmoney=" + wupinmoney +
                ", kechengmoney=" + kechengmoney +
                ", shishouTotalMoney=" + shishouTotalMoney +
                ", hetongMoney=" + hetongMoney +
                ", dingjing=" + dingjing +
                ", jiazhangDemand=" + jiazhangDemand +
                ", zhuanjieshaoID=" + zhuanjieshaoID +
                ", moneyStyle=" + moneyStyle +
                ", beizhu=" + beizhu +
                ", campusID=" + campusID +
                ", fromType=" + fromType +
                ", qiandanType=" + qiandanType +
                ", youhuiID=" + youhuiID +
                ", youhuijine=" + youhuijine +
                ", youhuishuoming=" + youhuishuoming +
                ", daijinquanmoney=" + daijinquanmoney +
                ", addstaffID=" + addstaffID +
                ", isdingjing=" + isdingjing +
                ", qdtype=" + qdtype +
                ", buxiStateID=" + buxiStateID +
                ", payState=" + payState +
                ", payDate=" + payDate +
                ", payUser=" + payUser +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

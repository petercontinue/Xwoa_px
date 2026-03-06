package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
@Data
@Accessors(chain = true)
@TableName("qiandanshenpi")
public class Qiandanshenpi extends Model<Qiandanshenpi> {

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
    @TableField("qiandandate")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date qiandandate;
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
     * 代金券金额
     */
    @TableField("daijinquanmoney")
    private BigDecimal daijinquanmoney;
    /**
     * 优惠说明
     */
    @TableField("youhuishuoming")
    private String youhuishuoming;
    @TableField("addstaffID")
    private Long addstaffID;
    /**
     * 1:全款（没有补交过尾款）；2：全款（补交过尾款）；3：定金；4：定金（交过尾款）
     */
    @TableField("isdingjing")
    private Integer isdingjing;
    /**
     * 学员状态1意向，2在读，3停课，4结课，5退费，6休眠，7新签待审批
     */
    @TableField("buxiStateID")
    private Integer buxiStateID;
    /**
     * 审批人
     */
    @TableField("shenpistaffID")
    private Long shenpistaffID;
    /**
     * 审批状态：0未审批；1审批不通过；2审批通过
     */
    @TableField("shenpiState")
    private Integer shenpiState;
    /**
     * 审批时间
     */
    @TableField("shenpiDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date shenpiDateTime;
    /**
     * 审批说明
     */
    @TableField("shenpishuoming")
    private String shenpishuoming;
    @TableField("hetongUrl")
    private String hetongUrl;
    /**
     * 新签还是续费：1新签；2续费
     */
    @TableField("isXinqianOrXufei")
    private Integer isXinqianOrXufei;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}

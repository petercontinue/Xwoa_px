package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-11-16
 */
@Data
@Accessors(chain = true)
public class Pxtuifeitable extends Model<Pxtuifeitable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 是对哪个签单操作退费
     */
    @TableField("qiandanID")
    private Long qiandanID;

    /**
     * 经过退费审批流程时的审批ID
     */
    @TableField("tuifeispID")
    private Long tuifeispID;

    /**
     * 给哪个学员退费
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 退费类型：1全科全退，2单科全退，3 单科部分退费，4退杂费，5退教学用品的费用，6退充值余额
     */
    @TableField("tuifeiType")
    private Integer tuifeiType;
    /**
     * 退费前的总剩余学费
     */
    @TableField("beforeTuifeiRemainXuefei")
    private BigDecimal beforeTuifeiRemainXuefei;
    /**
     * 应退费金额-系统计算金额
     */
    @TableField("shoudTuifeiTotalMoney")
    private BigDecimal shoudTuifeiTotalMoney;
    /**
     * 实际操作退费金额
     */
    @TableField("shijiTuifeiTotalMoney")
    private BigDecimal shijiTuifeiTotalMoney;
    /**
     * 退费后的总剩余学费
     */
    @TableField("afterTuifeiRemainXuefei")
    private BigDecimal afterTuifeiRemainXuefei;
    /**
     * 退费前积分数，没有填0
     */
    @TableField("beforeTuifeiJifen")
    private BigDecimal beforeTuifeiJifen;
    /**
     * 退了多少积分，没有填0
     */
    @TableField("tuijifen")
    private BigDecimal tuijifen;
    /**
     * 退费后积分数，没有填0
     */
    @TableField("afterTuifeiJifen")
    private BigDecimal afterTuifeiJifen;
    /**
     * 退费前的充值余额，没有填0
     */
    @TableField("beforeTFchongzhiRemainMoney")
    private BigDecimal beforeTFchongzhiRemainMoney;
    /**
     * 退了多少充值金额，没有填0
     */
    @TableField("tuiChongzhiMoney")
    private BigDecimal tuiChongzhiMoney;
    /**
     * 退费后的充值余额，没有填0
     */
    @TableField("afterTFchongzhiRemainMoney")
    private BigDecimal afterTFchongzhiRemainMoney;
    /**
     * 退费的支付方式
     */
    @TableField("tuifeiPayStyleID")
    private Long tuifeiPayStyleID;
    /**
     * 财务流水ID
     */
    @TableField("liushuiID")
    private Long liushuiID;
    /**
     * 备注说明，退课程课时或退教学用品的可详见相应的退费记录表
     */
    @TableField("beizhu")
    private String beizhu;
    @TableField("addStaffID")
    private Long addStaffID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("addTime")
    private Date addTime;
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
        return "Pxtuifeitable{" +
                ", id=" + id +
                ", qiandanID=" + qiandanID +
                ", tuifeispID=" + tuifeispID +
                ", stuID=" + stuID +
                ", tuifeiType=" + tuifeiType +
                ", beforeTuifeiRemainXuefei=" + beforeTuifeiRemainXuefei +
                ", shoudTuifeiTotalMoney=" + shoudTuifeiTotalMoney +
                ", shijiTuifeiTotalMoney=" + shijiTuifeiTotalMoney +
                ", afterTuifeiRemainXuefei=" + afterTuifeiRemainXuefei +
                ", beforeTuifeiJifen=" + beforeTuifeiJifen +
                ", tuijifen=" + tuijifen +
                ", afterTuifeiJifen=" + afterTuifeiJifen +
                ", beforeTFchongzhiRemainMoney=" + beforeTFchongzhiRemainMoney +
                ", tuiChongzhiMoney=" + tuiChongzhiMoney +
                ", afterTFchongzhiRemainMoney=" + afterTFchongzhiRemainMoney +
                ", tuifeiPayStyleID=" + tuifeiPayStyleID +
                ", liushuiID=" + liushuiID +
                ", beizhu=" + beizhu +
                ", addStaffID=" + addStaffID +
                ", addTime=" + addTime +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

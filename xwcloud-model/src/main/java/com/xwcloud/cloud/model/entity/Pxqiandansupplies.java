package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("pxqiandansupplies")
public class Pxqiandansupplies extends Model<Pxqiandansupplies> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 教学用品ID
     */
    @TableField("TeachingSuppliesID")
    private Long TeachingSuppliesID;
    /**
     * 名称
     */
    @TableField("Name")
    private String Name;
    /**
     * 单价
     */
    @TableField("BuyPrice")
    private BigDecimal BuyPrice;
    /**
     * 购买数量
     */
    @TableField("BuySum")
    private BigDecimal BuySum;
    /**
     * 签单ID
     */
    @TableField("QiandaninfoID")
    private Long QiandaninfoID;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 总金额
     */
    @TableField("SumMoney")
    private BigDecimal SumMoney;
    /**
     * 是否是退费.默认值false，退费时才填写该字段
     */
    @TableField("IsTuiFei")
    private Boolean IsTuiFei;
    /**
     * 1:已发放；2：未发放;默认1
     */
    @TableField("fafangstate")
    private Integer fafangstate;
    /**
     * 退费金额，退费时才填写该字段
     */
    @TableField("TuiMoney")
    private BigDecimal TuiMoney;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxqiandansupplies{" +
                ", id=" + id +
                ", TeachingSuppliesID=" + TeachingSuppliesID +
                ", Name=" + Name +
                ", BuyPrice=" + BuyPrice +
                ", BuySum=" + BuySum +
                ", QiandaninfoID=" + QiandaninfoID +
                ", stuID=" + stuID +
                ", SumMoney=" + SumMoney +
                ", IsTuiFei=" + IsTuiFei +
                ", fafangstate=" + fafangstate +
                ", TuiMoney=" + TuiMoney +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

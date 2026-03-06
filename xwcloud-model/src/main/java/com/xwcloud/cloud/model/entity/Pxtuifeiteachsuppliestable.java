package com.xwcloud.cloud.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class Pxtuifeiteachsuppliestable extends Model<Pxtuifeiteachsuppliestable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;


    @TableField("qdsupID")
    private Long qdsupID;
    /**
     * 退的教学用品的ID
     */
    @TableField("teachSuppliesID")
    private Long teachSuppliesID;
    /**
     * 退的教学用品的名称
     */
    @TableField("teachSuppliesName")
    private String teachSuppliesName;
    /**
     * 规格ID
     */
    @TableField("guige")
    private String guige;
    /**
     * 退前的剩余数量
     */
    @TableField("tfqianRemainNums")
    private BigDecimal tfqianRemainNums;
    /**
     * 退了多少
     */
    @TableField("tuiNums")
    private BigDecimal tuiNums;
    /**
     * 退后的剩余数量
     */
    @TableField("tfhouRemainNums")
    private BigDecimal tfhouRemainNums;
    /**
     * 教学用品的数量单位，例 个，盒，袋，箱，瓶……
     */
    @TableField("danwei")
    private String danwei;
    /**
     * 商品单价
     */
    @TableField("price")
    private BigDecimal price;
    /**
     * 单品备注
     */
    @TableField("beizhu")
    private String beizhu;
    /**
     * 退费ID
     */
    @TableField("tuifeiID")
    private Long tuifeiID;
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
        return "Pxtuifeiteachsuppliestable{" +
                ", id=" + id +
                ", qdsupID=" + qdsupID +
                ", teachSuppliesID=" + teachSuppliesID +
                ", teachSuppliesName=" + teachSuppliesName +
                ", guige=" + guige +
                ", tfqianRemainNums=" + tfqianRemainNums +
                ", tuiNums=" + tuiNums +
                ", tfhouRemainNums=" + tfhouRemainNums +
                ", danwei=" + danwei +
                ", price=" + price +
                ", beizhu=" + beizhu +
                ", tuifeiID=" + tuifeiID +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

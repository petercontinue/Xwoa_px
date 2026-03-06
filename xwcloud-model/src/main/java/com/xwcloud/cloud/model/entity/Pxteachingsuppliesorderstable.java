package com.xwcloud.cloud.model.entity;

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
 * @since 2020-11-21
 */
@Data
@Accessors(chain = true)
public class Pxteachingsuppliesorderstable extends Model<Pxteachingsuppliesorderstable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 订单号
     */
    @TableField("OrderNo")
    private String OrderNo;
    /**
     * 录入时间
     */
    @TableField("CreatDatetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date CreatDatetime;
    /**
     * 录入人
     */
    @TableField("CreatStaffID")
    private Long CreatStaffID;
    /**
     * 订单金额
     */
    @TableField("OrderMoney")
    private BigDecimal OrderMoney;
    /**
     * 优惠金额
     */
    @TableField("YouHuiMoney")
    private BigDecimal YouHuiMoney;
    /**
     * 实付金额
     */
    @TableField("ShijiPayMoney")
    private BigDecimal ShijiPayMoney;
    /**
     * 支付方式ID
     */
    @TableField("PayMoneyStyle")
    private String PayMoneyStyle;

    @TableField("OrderState")
    private Integer OrderState;

    @TableField("PayDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date PayDate;

    @TableField("qiyeID")
    private Long qiyeID;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxteachingsuppliesorderstable{" +
                ", id=" + id +
                ", OrderNo=" + OrderNo +
                ", CreatDatetime=" + CreatDatetime +
                ", CreatStaffID=" + CreatStaffID +
                ", OrderMoney=" + OrderMoney +
                ", YouHuiMoney=" + YouHuiMoney +
                ", ShijiPayMoney=" + ShijiPayMoney +
                ", PayMoneyStyle=" + PayMoneyStyle +
                ",OrderState=" + OrderState +
                ",PayDate=" + PayDate +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

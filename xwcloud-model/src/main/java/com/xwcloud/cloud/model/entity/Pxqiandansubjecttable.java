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
public class Pxqiandansubjecttable extends Model<Pxqiandansubjecttable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @TableField("stuID")
    private Long stuID;
    @TableField("qiandandate")
    private Date qiandandate;
    @TableField("kechengID")
    private Long kechengID;
    /**
     * 课程销售价（实际成交价，即分摊了优惠后的单价）
     */
    @TableField("kechengprice")
    private BigDecimal kechengprice;
    /**
     * 课程原价
     */
    @TableField("originalprice")
    private BigDecimal originalprice;
    /**
     * 购买课时数
     */
    @TableField("buykeshiNum")
    private BigDecimal buykeshiNum;
    /**
     * 总价
     */
    @TableField("zongjia")
    private BigDecimal zongjia;
    /**
     * 开始日期
     */
    @TableField("startDate")
    private Date startDate;
    /**
     * 结束日期
     */
    @TableField("endDate")
    private Date endDate;
    /**
     * 签单ID，关联签单表的ID
     */
    @TableField("qianDanInfoID")
    private Long qianDanInfoID;
    /**
     * 1买的 2 接受的赠送 3 送出的 4 退费 5 换课换出 6 换课得到
     */
    @TableField("kechengStyle")
    private Integer kechengStyle;
    /**
     * 折扣,10表示没打折
     */
    @TableField("discount")
    private BigDecimal discount;
    @TableField("qiyeID")
    private Long qiyeID;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxqiandansubjecttable{" +
                ", id=" + id +
                ", stuID=" + stuID +
                ", qiandandate=" + qiandandate +
                ", kechengID=" + kechengID +
                ", kechengprice=" + kechengprice +
                ", originalprice=" + originalprice +
                ", buykeshiNum=" + buykeshiNum +
                ", zongjia=" + zongjia +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", qianDanInfoID=" + qianDanInfoID +
                ", kechengStyle=" + kechengStyle +
                ", discount=" + discount +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

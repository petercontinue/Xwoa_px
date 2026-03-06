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
 * @since 2021-04-16
 */
@Data
@Accessors(chain = true)
@TableName("pxchongzhipaytable")
public class Pxchongzhipaytable extends Model<Pxchongzhipaytable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("stuID")
    private Long stuID;
    /**
     * 支出金额
     */
    @TableField("chongzhiPayMoney")
    private BigDecimal chongzhiPayMoney;
    /**
     * 1充值退费支出,2.报课支出 3.消课支出 4.进销存支出 5.结课删除
     */
    @TableField("type")
    private Integer type;
    /**
     * 详细备注支出明细
     */
    @TableField("beizhu")
    private String beizhu;
    /**
     * 签单ID，如果是报课支出，要填入本字段
     */
    @TableField("qiandanID")
    private Long qiandanID;
    /**
     * 退费ID，如果是退费支出，要填入本字段
     */
    @TableField("tuifeiID")
    private Long tuifeiID;
    /**
     * 消课ID，如果是消课支出，要填入本字段
     */
    @TableField("xiaokeID")
    private Long xiaokeID;
    /**
     * 进销存ID，如果是购买商品支出，要填入本字段
     */
    @TableField("jixiaocunID")
    private Long jixiaocunID;
    @TableField("addStaffID")
    private Long addStaffID;
    @TableField("addTime")
    private Date addTime;
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 消课ID时的课程ID
     */
    @TableField("kechengID")
    private Long kechengID;
    /**
     * 消课ID时的课程名称
     */
    @TableField("kechengName")
    private String kechengName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxchongzhipaytable{" +
                ", id=" + id +
                ", stuID=" + stuID +
                ", chongzhiPayMoney=" + chongzhiPayMoney +
                ", type=" + type +
                ", beizhu=" + beizhu +
                ", qiandanID=" + qiandanID +
                ", tuifeiID=" + tuifeiID +
                ", xiaokeID=" + xiaokeID +
                ", jixiaocunID=" + jixiaocunID +
                ", addStaffID=" + addStaffID +
                ", addTime=" + addTime +
                ", qiyeID=" + qiyeID +
                ", kechengID=" + kechengID +
                ", kechengName=" + kechengName +
                "}";
    }
}

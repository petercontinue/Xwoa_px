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
@TableName("qiandanshenpiyejiren")
public class Qiandanshenpiyejiren extends Model<Qiandanshenpiyejiren> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 签单审批ID
     */
    @TableField("qiandanshenpiID")
    private Long qiandanshenpiID;
    /**
     * 签单业绩人ID
     */
    @TableField("qiandanstaffID")
    private Long qiandanstaffID;
    /**
     * 签单业绩人业绩金额
     */
    @TableField("yejiMoney")
    private BigDecimal yejiMoney;
    /**
     * 签单日期
     */
    @TableField("yejidate")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date yejidate;
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
        return "Qiandanshenpiyejiren{" +
                ", id=" + id +
                ", qiandanshenpiID=" + qiandanshenpiID +
                ", qiandanstaffID=" + qiandanstaffID +
                ", yejiMoney=" + yejiMoney +
                ", yejidate=" + yejidate +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

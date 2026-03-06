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
@TableName("tuizafeiinfo")
public class Tuizafeiinfo extends Model<Tuizafeiinfo> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 退费学员
     */
    @TableField("stuID")
    private Long stuID;

    @TableField("qiandanInfo2ID")
    private Long qiandanInfo2ID;

    @TableField("qiandanOtherID")
    private Long qiandanOtherID;
    /**
     * 退费金额
     */
    @TableField("tuizfmoney")
    private BigDecimal tuizfmoney;
    /**
     * 添加人
     */
    @TableField("adduser")
    private Long adduser;
    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("addTime")
    private Date addTime;
    /**
     * 退费审批表ID
     */
    @TableField("tuifeispID")
    private Long tuifeispID;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Tuizafeiinfo{" +
                ", id=" + id +
                ", stuID=" + stuID +
                ", qiandanInfo2ID="+qiandanInfo2ID+
                ", qiandanOtherID=" + qiandanOtherID +
                ", tuizfmoney=" + tuizfmoney +
                ", adduser=" + adduser +
                ", addTime=" + addTime +
                ", tuifeispID=" + tuifeispID +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

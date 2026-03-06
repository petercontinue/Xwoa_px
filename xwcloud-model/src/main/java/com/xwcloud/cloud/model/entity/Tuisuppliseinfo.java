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
@TableName("tuisuppliseinfo")
public class Tuisuppliseinfo extends Model<Tuisuppliseinfo> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;


    @TableField("qiadansuppliesTabID")
    private Long qiadansuppliesTabID;
    /**
     * 退费的商品ID
     */

    @TableField("tuisuppliseID")
    private Long tuisuppliseID;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 退费商品的规格ID
     */
    @TableField("tuiguige")
    private String tuiguige;
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
    /**
     * 退费的商品金额
     */
    @TableField("tuimoney")
    private BigDecimal tuimoney;

    @TableField("tuinum")
    private BigDecimal tuinum;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Tuisuppliseinfo{" +
                ", id=" + id +
                ", qiadansuppliesTabID=" + qiadansuppliesTabID +
                ", tuisuppliseID=" + tuisuppliseID +
                ", stuID=" + stuID +
                ", tuiguige=" + tuiguige +
                ", adduser=" + adduser +
                ", addTime=" + addTime +
                ", tuifeispID=" + tuifeispID +
                ", tuimoney=" + tuimoney +
                ",tuinum=" + tuinum +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

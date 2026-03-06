package com.xwcloud.cloud.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-27
 */
@Data
@Accessors(chain = true)
@TableName("pxjieke")
public class Pxjieke extends Model<Pxjieke> {

    private static final long serialVersionUID = 1L;

    /**
     * 结课记录表ID
     */
    @TableId("id")
    private Long id;
    /**
     * 学员ID
     */
    @TableField("stuID")
    private Long stuID;
    /**
     * 结课添加人
     */
    @TableField("jkaddUser")
    private Long jkaddUser;
    /**
     * 结课时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("jiekeTime")
    private Date jiekeTime;
    /**
     * 结课说明
     */
    @TableField("jkshuoming")
    private String jkshuoming;
    /**
     * 结课前余额
     */
    @TableField("jiekeyue")
    private BigDecimal jiekeyue;
    /**
     * 结课前剩余学费
     */
    @TableField("jiekeremainxf")
    private BigDecimal jiekeremainxf;

    @TableField("chongzhipayID")
    private Long chongzhipayID;

    /**
     * 复课操作人
     */
    @TableField("fkaddUser")
    private Long fkaddUser;
    /**
     * 复课时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("fukeTime")
    private Date fukeTime;
    /**
     * 复课说明
     */
    @TableField("fkshuoming")
    private String fkshuoming;
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
        return "Pxjieke{" +
                ", id=" + id +
                ", stuID=" + stuID +
                ", jkaddUser=" + jkaddUser +
                ", jiekeTime=" + jiekeTime +
                ", jkshuoming=" + jkshuoming +
                ", jiekeyue=" + jiekeyue +
                ", jiekeremainxf=" + jiekeremainxf +
                ", chongzhipayID=" + chongzhipayID +
                ", fkaddUser=" + fkaddUser +
                ", fukeTime=" + fukeTime +
                ", fkshuoming=" + fkshuoming +
                ", qiyeID=" + qiyeID +
                "}";
    }
}

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
 * @since 2021-04-12
 */
@Data
@Accessors(chain = true)
@TableName("pxyouhuizhengcetable")
public class Pxyouhuizhengcetable extends Model<Pxyouhuizhengcetable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 1：折扣；2：满减
     */
    @TableField("youhuiType")
    private Integer youhuiType;
    /**
     * 限制金额,即达到多少金额享受优惠
     */
    @TableField("xianzhijine")
    private BigDecimal xianzhijine;
    /**
     * 优惠金额
     */
    @TableField("youhui")
    private BigDecimal youhui;
    /**
     * 活动开始时间
     */
    @TableField("startDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDateTime;
    /**
     * 活动结束时间
     */
    @TableField("endDatetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDatetime;
    /**
     * 校区ID
     */
    @TableField("campusID")
    private Long campusID;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 哪些年级可以用这个优惠政策。年级ID逗号分隔。为空表示通用（即所有年级均可使用）；
     */
    @TableField("stuGradeIDs")
    private String stuGradeIDs;
    /**
     * 是指优惠政策可以使用的次数；-1表示不限次数，默认值-1
     */
    @TableField("useTimes")
    private Integer useTimes;
    /**
     * 是否启用后台优惠政策：0不启用，1启用。默认0
     */
    @TableField("pxEnable")
    private Integer pxEnable;
    /**
     * 是否启用商城优惠政策：0不启用，1启用。默认0
     */
    @TableField("wscEnable")
    private Integer wscEnable;
    @TableField("addTime")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxyouhuizhengcetable{" +
                ", id=" + id +
                ", youhuiType=" + youhuiType +
                ", xianzhijine=" + xianzhijine +
                ", youhui=" + youhui +
                ", startDateTime=" + startDateTime +
                ", endDatetime=" + endDatetime +
                ", campusID=" + campusID +
                ", qiyeID=" + qiyeID +
                ", stuGradeIDs=" + stuGradeIDs +
                ", useTimes=" + useTimes +
                ", pxEnable=" + pxEnable +
                ", wscEnable=" + wscEnable +
                ", addTime=" + addTime +
                "}";
    }
}

package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>a
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Data
@Accessors(chain = true)
@TableName("whd_choujiang_cjrecord")
public class WhdChoujiangCjrecord extends Model<WhdChoujiangCjrecord> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 抽奖活动ID
     */
    @TableField("choujiangHuodongID")
    private Long choujianghuodongid;
    /**
     * 抽奖用户的微商城用户ID
     */
    @TableField("choujiangWxUserID")
    private Long choujiangwxuserid;
    /**
     * 奖品ID
     */
    @TableField("jiangpingID")
    private Long jiangpingid;
    /**
     * 奖品发放状态，0未放，1已发放
     */
    @TableField("jiangpingFafangState")
    private Integer jiangpingfafangstate;

    @TableField("jiangpingFFTime")
    private Date jiangpingFFTime;

    @TableField("FFstaffID")
    private Long FFstaffID;
    /**
     * 抽奖时间
     */
    @TableField("choujiangTime")
    private Date choujiangtime;
    /**
     * 说明
     */
    @TableField("shuoming")
    private String shuoming;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WhdChoujiangCjrecord{" +
                ", id=" + id +
                ", choujianghuodongid=" + choujianghuodongid +
                ", choujiangwxuserid=" + choujiangwxuserid +
                ", jiangpingid=" + jiangpingid +
                ", jiangpingfafangstate=" + jiangpingfafangstate +
                ", jiangpingFFTime=" + jiangpingFFTime +
                ", FFstaffID=" + FFstaffID +
                ", choujiangtime=" + choujiangtime +
                ", shuoming=" + shuoming +
                ", qiyeid=" + qiyeID +
                "}";
    }
}

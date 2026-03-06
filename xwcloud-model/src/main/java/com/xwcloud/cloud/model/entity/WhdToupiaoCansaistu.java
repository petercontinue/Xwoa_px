package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("whd_toupiao_cansaistu")
public class WhdToupiaoCansaistu extends Model<WhdToupiaoCansaistu> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 参加投票的学员姓名
     */
    @TableField("stuName")
        private String stuname;
    /**
     * 参赛宣言
     */
    @TableField("xuanYan")
    private String xuanyan;
    /**
     * 个人简介
     */
    @TableField("introduction")
    private String introduction;
    /**
     * 头像
     */
    @TableField("logo")
    private String logo;
    /**
     * 浏览次数，默认值0
     */
    @TableField("lookTimes")
    private Integer looktimes;
    /**
     * 得到的投票数，默认值0
     */
    @TableField("piaoshu")
    private Integer piaoshu;

    /**
     * 参赛学员编号
     */
    @TableField("bianhao")
    private Integer bianhao;
    /**
     * 投票活动ID
     */
    @TableField("toupiaoHuodongID")
    private Long toupiaohuodongid;
    /**
     * 添加人
     */
    @TableField("addUser")
    private Long adduser;
    /**
     * 添加时间
     */
    @TableField("addTime")
    private LocalDateTime addtime;
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
        return "WhdToupiaoCansaistu{" +
                ", id=" + id +
                ", stuname=" + stuname +
                ", xuanyan=" + xuanyan +
                ", introduction=" + introduction +
                ", logo=" + logo +
                ", looktimes=" + looktimes +
                ", piaoshu=" + piaoshu +
                ", toupiaohuodongid=" + toupiaohuodongid +
                ", adduser=" + adduser +
                ", addtime=" + addtime +
                ", qiyeid=" + qiyeID +
                "}";
    }
}

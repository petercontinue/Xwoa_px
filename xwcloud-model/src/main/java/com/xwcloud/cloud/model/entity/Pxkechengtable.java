package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Data
@Accessors(chain = true)
@TableName("pxkechengtable")
public class Pxkechengtable extends Model<Pxkechengtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 课程名称
     */
    @TableField("kechengName")
    private String kechengName;
    /**
     * 科目ID
     */
    @TableField("subjectID")
    private Long subjectID;
    /**
     * 补习方式ID
     */
    @TableField("buxiStyleID")
    private Long buxiStyleID;
    /**
     * 是不是一对一课程，0不是，1是
     */
    @TableField("is1v1KC")
    private Integer is1v1KC;
    /**
     * 课程时长ID
     */
    @TableField("classTimeStyleID")
    private Long classTimeStyleID;
    /**
     * 课程原价
     */
    @TableField("kechengOriginalPrice")
    private BigDecimal kechengOriginalPrice;
    /**
     * 课程销售价
     */
    @TableField("kechengprice")
    private BigDecimal kechengprice;
    /**
     * 课时数,保留两位小数位
     */
    @TableField("keshiNum")
    private BigDecimal keshiNum;

    @TableField("byMonthOrDay")
    private Integer byMonthOrDay;
    /**
     * 课程销售总价
     */
    @TableField("buyZonjia")
    private BigDecimal buyZonjia;
    /**
     * 是否启用,0不启用，1启用，默认值1
     */
    @TableField("isShow")
    private Integer isShow;
    /**
     * 课程证书ID
     */
    @TableField("ZSid")
    private Long zSid;
    /**
     * 课程计费方式ID,1按课时计费，2按课时包，3按起止日期
     */
    @TableField("jifeiStyleID")
    private Integer jifeiStyleID;
    /**
     * 课程校区ID
     */
    @TableField("campusID")
    private Long campusID;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 背景颜色
     */
    @TableField("bgColor")
    private String bgColor;
    /**
     * 每多少天可以请假qingjiaTimes,0:不限制
     */
    @TableField("perdaysqj")
    private Integer perdaysqj;
    /**
     * 每多少课时可以请假qingjiaTimes,0:不限制
     */
    @TableField("perkeshiqj")
    private BigDecimal perkeshiqj;
    /**
     * 请假次数,-1表示不限制
     */
    @TableField("qingjiaTimes")
    private Integer qingjiaTimes;
    /**
     * 请假是否扣课时 True 要扣课时  false：不扣课时
     */
    @TableField("iskoukeshi")
    private Boolean iskoukeshi;
    /**
     * 课程图片，小程序端显示
     */
    @TableField("kechengImg")
    private String kechengImg;
    /**
     * 课程备注信息
     */
    @TableField("kechengbeizhu")
    private String kechengbeizhu;
    /**
     * 课程详情（小程序端显示）
     */
    @TableField("kechengcontent")
    private String kechengcontent;
    /**
     * 移动端是否显示课程0：不显示；1：显示
     */
    @TableField("showInApp")
    private Integer showInApp;
    /**
     * 刷卡时间段，1课前，2课中，3课后，默认值1
     */
    @TableField("shuakaTime")
    private Integer shuakaTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxkechengtable{" +
                ", id=" + id +
                ", kechengName=" + kechengName +
                ", subjectID=" + subjectID +
                ", buxiStyleID=" + buxiStyleID +
                ", is1v1KC=" + is1v1KC +
                ", classTimeStyleID=" + classTimeStyleID +
                ", kechengOriginalPrice=" + kechengOriginalPrice +
                ", kechengprice=" + kechengprice +
                ",byMonthOrDay=" + byMonthOrDay +
                ", keshiNum=" + keshiNum +
                ", buyZonjia=" + buyZonjia +
                ", isShow=" + isShow +
                ", zSid=" + zSid +
                ", jifeiStyleID=" + jifeiStyleID +
                ", campusID=" + campusID +
                ", qiyeID=" + qiyeID +
                ", bgColor=" + bgColor +
                ", perdaysqj=" + perdaysqj +
                ", perkeshiqj=" + perkeshiqj +
                ", qingjiaTimes=" + qingjiaTimes +
                ", iskoukeshi=" + iskoukeshi +
                ", kechengImg=" + kechengImg +
                ", kechengbeizhu=" + kechengbeizhu +
                ", kechengcontent=" + kechengcontent +
                ", showInApp=" + showInApp +
                ", shuakaTime=" + shuakaTime +
                "}";
    }
}

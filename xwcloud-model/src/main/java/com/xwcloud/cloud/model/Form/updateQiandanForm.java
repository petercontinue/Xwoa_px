package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 修改签单提交表单
 */
@Data
public class updateQiandanForm {
    /**
     * 签单ID
     */
    @ApiModelProperty(value = "签单ID")
    public Long qiandanid;
    /**
     * 补习课程金额
     */
    @ApiModelProperty(value = "补习课程金额")
    public BigDecimal buxikechengMoney;
    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    public String beizhu;
    /**
     * 补习课程数据
     */
    @ApiModelProperty(value = "补习课程数据")
    public String bxKcData;
    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    public String stuName;
    /**
     * 学生性别
     */
    @ApiModelProperty(value = "学生性别")
    public String stuSex;
    /**
     * 所在校区
     */
    @ApiModelProperty(value = "所在校区")
    public Long campusID;
    /**
     * 学生年级
     */
    @ApiModelProperty(value = "学生年级")
    public Long stuGradeID;
    /**
     * 补习状态
     */
    @ApiModelProperty(value = "补习状态")
    public Integer buxiStateID;
    /**
     * 父母联系电话
     */
    @ApiModelProperty(value = "父母联系电话")
    public String parentsTel;
    /**
     * 签单业绩人信息
     */
    @ApiModelProperty(value = "签单业绩人信息")
    public String qiandanstaffinfo;
    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    public String paymoneystyle;
    /**
     * 是否转介绍
     */
    @ApiModelProperty(value = "是否转介绍")
    public String isZhuanIntroduce;
    /**
     * 签单日期
     */
    @ApiModelProperty(value = "签单日期")
    public String qiandandate;
    /**
     * 学生来源
     */
    @ApiModelProperty(value = "学生来源")
    public Long telFromID;
    /**
     * 原就读学校
     */
    @ApiModelProperty(value = "原就读学校")
    public String oldSname;
    /**
     * 原校联系老师
     */
    @ApiModelProperty(value = "原校联系老师")
    public String oldTname;
    /**
     * 自定义属性
     */
    @ApiModelProperty(value = "自定义属性")
    public String shuxingValue;
    public String moneyValue;
    /**
     * 身份证ID
     */
    @ApiModelProperty(value = "身份证ID")
    public String numberID;
    /**
     * 学员生日
     */
    @ApiModelProperty(value = "学员生日")
    public String stuBrithday;
    /**
     * 定金金额
     */
    @ApiModelProperty(value = "定金金额")
    public BigDecimal dingjin;
    /**
     * 代金券金额
     */
    @ApiModelProperty(value = "代金券金额")
    public BigDecimal daijinquan;
    /**
     * 删除课程信息
     */
    @ApiModelProperty(value = "删除课程信息")
    public String removedKCstr;
    /**
     * 物品信息
     */
    @ApiModelProperty(value = "物品信息")
    public String wpData;
    /**
     * 自定义学号
     */
    @ApiModelProperty(value = "自定义学号")
    public String zidingyiStuNO;
    /**
     * 物品总价
     */
    @ApiModelProperty(value = "物品总价")
    public BigDecimal wpPrice;
    /**
     * 杂费信息
     */
    @ApiModelProperty(value = "杂费信息")
    public String zfData;
    /**
     * 杂费总价
     */
    @ApiModelProperty(value = "杂费总价")
    public BigDecimal zfAmount;
    /**
     * 优惠券ID
     */
    @ApiModelProperty(value = "优惠券ID")
    public Long selectYouhui;
    /**
     * 删除物品信息
     */
    @ApiModelProperty(value = "删除物品信息")
    public String removeWPstr;
    /**
     * 删除杂费信息
     */
    @ApiModelProperty(value = "删除杂费信息")
    public String removeZFstr;
}

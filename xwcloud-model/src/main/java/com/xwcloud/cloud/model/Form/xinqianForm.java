package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新签提交信息类
 */
@Data
public class xinqianForm {
    /**
     * 意向充值，新生充值才有stuID
     */
    @ApiModelProperty(value = "学生ID")
    public long stuid;
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
     * 校区ID
     */
    @ApiModelProperty(value = "校区ID")
    public Long campusID;
    /**
     * 学生年级
     */
    @ApiModelProperty(value = "学生年级")
    public Long stuGradeID;
    /**
     * 就读状态
     */
    @ApiModelProperty(value = "就读状态")
    public Integer buxiStateID;
    /**
     * 父母联系电话
     */
    @ApiModelProperty(value = "父母联系电话")
    public String parentsTel;
    /**
     * 联系电话关系
     */
    @ApiModelProperty(value = "联系电话关系")
    private String parentTelRelation;
    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    public Long payMoneyStyle;
    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    public Long telFromID;
    /**
     * 签单人
     */
    @ApiModelProperty(value = "签单人")
    public Long qianDanStaffID;
    /**
     * 签单日期
     */
    @ApiModelProperty(value = "签单日期")
    public String qiandandate;
    /**
     * 自定义学号
     */
    @ApiModelProperty(value = "自定义学号")
    public String zidingyiStuNO;
    /**
     * 是不是转介绍  转介绍ID,如果为0或为空，即不是转介绍,否则对应pxqiandanzhuanjieshaotable表的ID
     */

    @ApiModelProperty(value = "转介绍ID,如果为0或为空，即不是转介绍,否则对应pxqiandanzhuanjieshaotable表的ID")
    public String zhuanIntroduce;
    /**
     * 转介绍学员ID
     */

    @ApiModelProperty(value = "转介绍学员ID")
    public String zhuanjieshaoStuID;
    /**
     * 转介绍老师ID
     */

    @ApiModelProperty(value = "转介绍老师ID")
    public String zhuanjieshaoTeacherID;
    /**
     * 学员生日
     */
    @ApiModelProperty(value = "学员生日")
    public String stuBrithday;
    /**
     * 原就读学校
     */
    @ApiModelProperty(value = "原就读学校")
    public String oldSchoolID;
    /**
     * 原校联系老师
     */
    @ApiModelProperty(value = "原校联系老师")
    public String oldSchoolTeacherID;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    public String beizhu;
    /**
     * 代金券金额
     */
    @ApiModelProperty(value = "代金券金额")
    public BigDecimal daijinquan;
    /**
     * 定金金额
     */
    @ApiModelProperty(value = "定金金额")
    public BigDecimal dingjin;

    @ApiModelProperty(value = "小程序支付")
    public String isappPay;

    /**
     * 课程总费用
     */
    @ApiModelProperty(value = "课程总费用")
    public BigDecimal amountKC;
    /**
     * 物品总费用
     */
    @ApiModelProperty(value = "物品总费用")
    public BigDecimal amountWp;
    /**
     * 其他费用总额
     */
    @ApiModelProperty(value = "其他费用总额")
    public BigDecimal amountOther;
    /**
     * 合计总费用
     */
    @ApiModelProperty(value = "合计总费用")
    public BigDecimal amountMoney;
    /**
     * 实际总费用
     */
    @ApiModelProperty(value = "实际总费用")
    public BigDecimal amountShiji;
    /**
     * 实际总额
     */
    @ApiModelProperty(value = "实际总额")
    public BigDecimal shijiAmount;
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
     * 物品数据
     */
    @ApiModelProperty(value = "物品数据")
    public String wpData;
    /**
     * 课程数据
     */
    @ApiModelProperty(value = "课程数据")
    public String kcData;
    /**
     * 其他费用数据
     */
    @ApiModelProperty(value = "其他费用数据")
    public String othermoneydata;
    /**
     * 签单业绩人信息
     */
    @ApiModelProperty(value = "签单业绩人信息")
    public String qiandanstaffinfo;
    /**
     * 支付方式信息
     */
    @ApiModelProperty(value = "支付方式信息")
    public String paytyles;
    /**
     * 优惠政策
     */
    @ApiModelProperty(value = "优惠政策")
    public Long youhuizhengce;
    /**
     * 意向学员ID
     */
    @ApiModelProperty(value = "意向学员ID")
    public Long yxStuID;

    @ApiModelProperty(value = "自定义字段")
    public String stuparams;

    @ApiModelProperty(value = "家长要求")
    public String jiazhangDemand;
}

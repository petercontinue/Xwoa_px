package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class updateqiandanVO {
    private long id;
    private long stuID;
    private String stuName;
    private String stuSex;
    private long campusID;
    private long stuGradeID;
    private Integer buxiStateID;
    private String parentsTel;
    private String qianDanStaff;
    private String PayMoneyStyle;
    private Integer isZhuanIntroduce;
    private String qiandandate;
    private long telFromID;
    private String oldSchoolID;
    private String oldSchoolTeacherID;
    private String StuBrithday;
    private String IDnumber;
    private String IDImage;
    private String ruxuechengji;
    private String laoshiyaoqiu;
    private String jijixing;
    private String xingge;
    private BigDecimal HetongMoney;
    private BigDecimal shishouTotalMoney;
    private String beizhu;
    private long youhuiID;
    private BigDecimal youhuijine;
    private String youhuishuoming;//优惠说明
    private BigDecimal daijinquanMoney;//代金券金额
    private BigDecimal kechengMoney;
    private BigDecimal zafeiMoney;
    private BigDecimal wupingMoney;
    private String zidingyiStuNo;
    private String parentTelRelation;
}

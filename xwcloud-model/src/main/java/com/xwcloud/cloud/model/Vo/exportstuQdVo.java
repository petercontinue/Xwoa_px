package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
public class exportstuQdVo {
    private String campusName;
    private String stuID;
    private String stuName;
    private String stuGradeName;
    private String parentTel;
    private String stuSex;
    private String beizhu;
    private String roomids;
    private String stuTel;
    private String HetongMoney;
    private String shishouTotalMoney;
    private String zaiMoney; //杂费
    private String money; //代金券
    private Date qiandandate;
    private String zhuanjieshao; //转介绍
    private String staffName; //经办人
    private String moneyStyle; //费用类型
    private String moneystyleName; //支付方式
}

package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class shenpiVo {
    private String id;
    private String stuID;
    private String zidingyiStuID;
    private String stuName;
    private int type; //退费类型
    private BigDecimal sqtuiMoney; //退金额
    private String moneystyleName; //支付方式
    private Date tuifeibanlidate; //退费办理的时间
    private Date chuliTime; //审批处理时间
    private String yejistaff; //业绩人
    private String tuifeishuoming; //退费说明
    private String shenqinren; ///申请人
    private String shenpiren; ///审批人
    private int spfinish; //审批状态
    private String spshuoming; //审批结果说明
}


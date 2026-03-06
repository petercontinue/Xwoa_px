package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//课程转送
@Data
public class transferVo {
    private String id;//转送ID
    private String songcampus;//送出学员的校区
    private String songstuID;//送出x学员的学号
    private String zidingyiStuIDa;//送出x学员的zd学号
    private String songstu;//送出的学员
    private String songkc;//送出的课程
    private BigDecimal songKeshiNum;//送出的课时
    private BigDecimal songkechengPrice;//送出的课程价格
    private BigDecimal allshengyukeshi;//送出前的全部剩余课时
    private String shoucampus;//接收学员的校区
    private String shoustuID;//接收学员的ID
    private String zidingyiStuIDb;//接收x学员的zd学号
    private String shoustu;//接收的学员
    private String shoukc;//接收的课程
    private BigDecimal shouKeshi;//接收的课时
    private BigDecimal shouyukeshi;//接收的全部剩余课时
    private String shuoMing;//转送说明
    private String jingbanren;//处理人
    private Date zhuansongDate;//转送的日期
    private BigDecimal shoukechengPrice;//接收的课程价格
    private String songjifeiStyleID;
    private String shoujifeiStyleID;
}

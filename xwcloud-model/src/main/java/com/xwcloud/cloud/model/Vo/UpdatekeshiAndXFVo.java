package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
/**
*@Description: 作用:修改学费、课时
*@param:
*@return:
*@auter:yyl
*@data:2020/12/8 10:39
*/
public class UpdatekeshiAndXFVo {
    private String id;
    private String campusName;
    private String stuID;
    private String stuName;
    private String zidingyiStuID;//自定义学号
    private String subjectName;
    private String kechengName;
    private BigDecimal OldPrice;//原单价
    private BigDecimal kechengprice;//现单价
    private BigDecimal remainkeshi;//剩余课时
    private BigDecimal remainXuefei;//剩余学费
}

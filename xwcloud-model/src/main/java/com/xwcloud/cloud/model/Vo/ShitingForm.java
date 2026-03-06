package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShitingForm {

    @ApiModelProperty("试听ID")
    private Long id;
    @ApiModelProperty("学生ID")
    private Long stuID;
    @ApiModelProperty("班级ID")
    private Long classID;
    @ApiModelProperty("排课ID")
    private Long paikeID;
    @ApiModelProperty("课程ID")
    private Long kechengID;
    @ApiModelProperty("教师ID")
    private Long staffID;
    @ApiModelProperty("教师ID")
    private Long classRoomID;
    @ApiModelProperty("上课日期")
    private String haveClassDate;
    @ApiModelProperty("上下课时间格式HH:mm-HH:ss")
    private String haveClassTime;
    @ApiModelProperty("排课冲突检测")
    private Boolean ctjcSwitch;
    @ApiModelProperty("是否计入上课人数")
    private Boolean isKechengStuNum;
    @ApiModelProperty("试听单价")
    private BigDecimal shitingPrice;
    @ApiModelProperty(value = "支付方式")
    private Long payMoneyStyle;
}

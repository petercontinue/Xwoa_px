package com.xwcloud.cloud.model.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class yxqiandanVo {
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    @ApiModelProperty(value = "签单金额")
    private BigDecimal qianDanMoney;
    @ApiModelProperty(value = "报名签单还是充值")
    private String isBaomingOrChongzhi;
    @ApiModelProperty(value = "市场人名字")
    private String scStaffName;
    @ApiModelProperty(value = "负责人姓名")
    private String fzStaffName;
    @ApiModelProperty(value = "签单人姓名")
    private String qdStaffName;
    @ApiModelProperty(value = "登记时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date yxDengjiDateTime;
    @ApiModelProperty(value = "签单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date yxQiandanDateTime;
}

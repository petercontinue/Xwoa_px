package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class buyKechengVo {
    @ApiModelProperty(value = "数据ID")
    public Long id;
    @ApiModelProperty(value = "补习课程ID")
    public Long buxiID;
    @ApiModelProperty(value = "是否显示")
    public Integer isShow;
    @ApiModelProperty(value = "课程名称")
    public String kcName;
    @ApiModelProperty(value = "补习方式名称")
    public String pxStyleName;
    @ApiModelProperty(value = "科目名称")
    public String kmName;
    @ApiModelProperty(value = "班级名称")
    public String bjName;
    @ApiModelProperty(value = "班级ID")
    public Long classID;
    @ApiModelProperty(value = "排课ID")
    public Long pkid;
    @ApiModelProperty(value = "课程ID")
    public Long kechengID;
    @ApiModelProperty(value = "补习方式ID")
    public Long buxistykeID;
    @ApiModelProperty(value = "课程ID")
    public Long KCID;
    @ApiModelProperty(value = "原单价")
    public BigDecimal YDJ;
    @ApiModelProperty(value = "单价")
    public BigDecimal DJ;
    @ApiModelProperty(value = "课时")
    public BigDecimal KS;
    public BigDecimal ZKS;
    public BigDecimal RKS;
    public BigDecimal ZJ;
    public BigDecimal ZK;
    public String startDate;
    public String endDate;
    public int jifeistyle;
    public Boolean ckb;
    public Long qdsID;
}

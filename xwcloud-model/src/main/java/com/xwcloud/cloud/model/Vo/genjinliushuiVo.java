package com.xwcloud.cloud.model.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class genjinliushuiVo {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    @ApiModelProperty(value = "跟进内容")
    private String gengjinText;
    @ApiModelProperty(value = "跟进时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gengjinTime;
    @ApiModelProperty(value = "跟进人姓名")
    private String staffName;
    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;
    @ApiModelProperty(value = "负责人姓名")
    private String fuzeStaffName;
}

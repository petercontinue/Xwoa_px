package com.xwcloud.cloud.model.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class yaoyueVo {
    @ApiModelProperty(value = "邀约ID")
    private String id;
    private String stuID;
    @ApiModelProperty(value = "意向学员姓名")
    private String stuName;
    @ApiModelProperty(value = "意向学员性别")
    private String stuSex;
    @ApiModelProperty(value = "学生年级")
    private String stuGradeName;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "邀约时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date invitationTime;
    @ApiModelProperty(value = "邀约状态")
    private String invitationZhuangtai;
    @ApiModelProperty(value = "邀约说明")
    private String shuoMing;
    @ApiModelProperty(value = "到访时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date daofangDatetime;
}

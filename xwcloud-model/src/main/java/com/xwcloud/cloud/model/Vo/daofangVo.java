package com.xwcloud.cloud.model.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class daofangVo {
    @ApiModelProperty(value = "到访ID")
    private String id;
    @ApiModelProperty(value = "邀约ID")
    private Long invitationID;
    @ApiModelProperty(value = "意向学员姓名")
    private String stuName;
    @ApiModelProperty(value = "意向学员性别")
    private String stuSex;
    @ApiModelProperty(value = "学生年级")
    private String stuGradeName;
    private String stuGradeID;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    private String campusID;
    @ApiModelProperty(value = "邀约时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date invitationTime;
    @ApiModelProperty(value = "到访时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date daofangDatetime;
    @ApiModelProperty(value = "到访记录")
    private String daofangtext;
    @ApiModelProperty(value = "邀约结果")
    private String invitationZhuangtai;
}

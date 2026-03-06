package com.xwcloud.cloud.model.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class yaoyuedaofangVo {
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    @ApiModelProperty(value = "邀约时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date invitationTime;
    @ApiModelProperty(value = "邀约状态")
    private String invitationZhuangtai;
    @ApiModelProperty(value = "邀约人姓名")
    private String staffName;
    @ApiModelProperty(value = "到访时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date daofangDate;
    @ApiModelProperty(value = "到访人姓名")
    private String daofangStaffName;
    @ApiModelProperty(value = "说明")
    private String shuoMing;
}

package com.xwcloud.cloud.model.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xwcloud.cloud.model.entity.Pxstutable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class yixiangStuVo extends Pxstutable {
    @ApiModelProperty(value = "学生年级")
    private String stuGradeName;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "学生来源")
    private String telFromName;
    @ApiModelProperty(value = "意向科目")
    private String yixiangkecheng;
    @ApiModelProperty(value = "意向程度")
    private String yxLeveName;
    @ApiModelProperty(value = "跟进次数")
    private Integer genjinSum;
//    @ApiModelProperty(value = "下次跟进时间")
//    private Date nextGenjinTime;
    @ApiModelProperty(value = "邀约次数")
    private Integer yaoyueSum;
    @ApiModelProperty(value = "到访次数")
    private Integer daofangSum;
    @ApiModelProperty(value = "试听次数")
    private Integer shitingSum;
    @ApiModelProperty(value = "负责人")
    private String fuzeStaffName;
    @ApiModelProperty(value = "登记人姓名")
    private String dengjiStaffName;
    @ApiModelProperty(value = "市场人姓名")
    private String shichangStaffName;
    @ApiModelProperty(value = "意向程度")
    private String telLevelName;
    @ApiModelProperty(value = "最后一次跟进时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastGenjinTime;
    private String yxshichangTeaName;

}

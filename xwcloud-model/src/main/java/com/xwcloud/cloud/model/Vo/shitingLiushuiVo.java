package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxshitingrecordtable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class shitingLiushuiVo extends Pxshitingrecordtable {
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    @ApiModelProperty(value = "教室名称")
    private String classRoomName;
    @ApiModelProperty(value = "班级名称")
    private String className;
    @ApiModelProperty(value = "试听上课时间")
    private String haveLessTime;
    @ApiModelProperty(value = "试听老师")
    private String stTeachers;
    @ApiModelProperty(value = "录入人姓名")
    private String staffName;
    @ApiModelProperty(value = "满意度")
    private String shiTingManyidu;
    @ApiModelProperty(value = "试听还是一对一")
    private String chabanOr1v1Value;

    private String payMoneyStyle;

}

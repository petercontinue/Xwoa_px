package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class stuValueFrom {

    @ApiModelProperty(value = "学员ID", required = true)
    private String stuID;

    @ApiModelProperty(value = "自定义学号", required = true)
    private String zidingyiStuID;

    @ApiModelProperty(value = "学员名称", required = true)
    private String stuName;

    @ApiModelProperty(value = "联系电话", required = true)
    private String parentTel;

    @ApiModelProperty(value = "校区ID", required = true)
    private String campusID;

    @ApiModelProperty(value = "学员性别", required = true)
    private String stuSex;

    @ApiModelProperty(value = "联系电话关系", required = true)
    private String parentTelRelation;

    @ApiModelProperty(value = "年级ID", required = true)
    private String stuGradeID;

    @ApiModelProperty(value = "生日", required = true)
    private String stubirth;

    @ApiModelProperty(value = "学员电话", required = true)
    private String stuTel;

    @ApiModelProperty(value = "原就读学校", required = true)
    private String oldSchool;

    @ApiModelProperty(value = "合作老师", required = true)
    private String oldSchoolTeacher;

    @ApiModelProperty(value = "登记人", required = true)
    private String dengjiTeacherID;

    @ApiModelProperty(value = "备注", required = true)
    private String stuXuexi;

    @ApiModelProperty(value = "合作老师联系电话", required = true)
    private String oldTeacherTel;

    @ApiModelProperty(value = "全部数据", required = true)
    private String alldata;


}



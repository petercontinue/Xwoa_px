package com.xwcloud.cloud.model.Form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AddYxstuForm {
    @ApiModelProperty(value = "年级")
    private Long gradeID;
    @ApiModelProperty(value = "意向学员ID", required = false)
    private Long yxstuID;
    @ApiModelProperty(value = "市场人ID", required = true)
    private Long shichangrenID;
    @ApiModelProperty(value = "学生姓名", required = true)
    private String stuName;
    @ApiModelProperty(value = "学生年级ID", required = false)
    private Long stuGradeID;
    @ApiModelProperty(value = "学员生日", required = false)
    private Date stuBirthday;
    @ApiModelProperty(value = "意向科目", required = false)
    private String yixiangkemu;
    @ApiModelProperty(value = "联系电话", required = false)
    private String telphone;
    @ApiModelProperty(value = "父母联系电话", required = false)
    private String parenttelphone;
    @ApiModelProperty(value = "学生性别", required = false)
    private String stuSex;
    @ApiModelProperty(value = "校区ID", required = false)
    private Long campusID;
    @ApiModelProperty(value = "学生来源ID", required = false)
    private Long telFromID;
    @ApiModelProperty(value = "意向程度ID", required = false)
    private Long telLevelID;
    @ApiModelProperty(value = "电话关系", required = false)
    private String guanxi;
    @ApiModelProperty(value = "备注信息", required = false)
    private String beizhu;
    @ApiModelProperty(value = "学生自定义属性值", required = false)
    private String shuxingValue;
    @ApiModelProperty(value = "企业ID", required = true)
    private String qiyeID;
}

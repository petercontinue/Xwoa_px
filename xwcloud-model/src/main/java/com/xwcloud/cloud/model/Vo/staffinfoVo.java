package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class staffinfoVo {
    @ApiModelProperty(value = "员工ID")
    private String id;
    @ApiModelProperty(value = "员工姓名")
    private String staffName;
    @ApiModelProperty(value = "员工联系电话")
    private String staffTel;
    @ApiModelProperty(value = "性别")
    private String staffSex;
    @ApiModelProperty(value = "员工状态")
    private Integer staffState;
    @ApiModelProperty(value = "入职时间")
    private String joinTime;
    @ApiModelProperty(value = "校区ID")
    private String campusID;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    private Long staffPostID;
    @ApiModelProperty(value = "岗位名称")
    private String staffpostName;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "员工生日")
    private String staffBirthday;
    private String role;
    private String teaSubject;//任教科目
    private  String shuoMing;
    private String jiaoxueJingyan;
}

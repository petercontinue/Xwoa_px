package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
public class AddChongzhiForm {
    @ApiModelProperty(value = "校区ID", required = true)
    private Long campusID;
    @ApiModelProperty(value = "学生补习状态", required = true)
    private Integer stuState;
    @ApiModelProperty(value = "学生年级", required = true)
    private Long stuGradeId;
    @ApiModelProperty(value = "学生联系电话", required = true)
    private String stuTel;
    @ApiModelProperty(value = "学生姓名", required = true)
    private String stuName;
    @ApiModelProperty(value = "支付方式", required = true)
    private String payMoneyStyleId;
    @ApiModelProperty(value = "业绩人ID", required = true)
    private Long yeJiRenId;
    @ApiModelProperty(value = "充值金额", required = true)
    private BigDecimal chongzhiMoney;
    @ApiModelProperty(value = "赠送金额", required = true)
    private BigDecimal zengsongMoney;
    @ApiModelProperty(value = "实得总金额", required = true)
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "添加充值时间", required = false)
    private String addDateTime;
    @ApiModelProperty(value = "学生生日", required = false)
    private String stuBirthday;
    @ApiModelProperty(value = "学生身份证", required = false)
    private String stuIdentityNumber;
    @ApiModelProperty(value = "原就读学校", required = false)
    private String oldSchoolID;
    @ApiModelProperty(value = "原就读学校联系老师", required = false)
    private String oldSchoolTeacherID;
    @ApiModelProperty(value = "意向学员ID", required = false)
    private Long yxid;
    @ApiModelProperty(value = "支付方式及金额",required = false)
    private String paystylemoney;
}

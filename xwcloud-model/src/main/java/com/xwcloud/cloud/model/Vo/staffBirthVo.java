package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class staffBirthVo {
    @ApiModelProperty(value = "数据ID")
    public String id;
    @ApiModelProperty(value = "校区名称")
    public String CampusName;
    @ApiModelProperty(value = "员工岗位名称")
    public String StaffPostName;
    @ApiModelProperty(value = "员工联系电话")
    public String StaffTel;
    @ApiModelProperty(value = "员工生日")
    public String StaffBirthDay;
    @ApiModelProperty(value = "员工姓名")
    public  String staffName;
}

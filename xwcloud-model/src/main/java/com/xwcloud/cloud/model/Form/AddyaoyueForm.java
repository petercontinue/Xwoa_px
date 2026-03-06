package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AddyaoyueForm {
    @ApiModelProperty(value = "员工ID", required = true)
    private Long staffID;
    @ApiModelProperty(value = "意向学员ID", required = true)
    private Long stuID;
    @ApiModelProperty(value = "邀约状态", required = true)
    private String yaoyuezhuangtai;
    @ApiModelProperty(value = "邀约时间", required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date yaoyuedate;
    @ApiModelProperty(value = "说明", required = true)
    private String shuoming;
    @ApiModelProperty(value = "邀约记录ID，修改时使用", required = false)
    private Long yaoyueID;
    @ApiModelProperty(value = "企业ID", required = true)
    private String qiyeID;
}

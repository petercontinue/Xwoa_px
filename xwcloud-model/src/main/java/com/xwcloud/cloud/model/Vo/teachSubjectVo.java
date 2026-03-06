package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class teachSubjectVo {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "员工校区名称")
    private String staffCampusName;
    @ApiModelProperty(value = "员工姓名")
    private String staffName;
    @ApiModelProperty(value = "科目名称")
    private String subjectName;
    @ApiModelProperty(value = "任教校区名称")
    private String teachCampusName;
    @ApiModelProperty(value = "说明")
    private String shuoming;
}

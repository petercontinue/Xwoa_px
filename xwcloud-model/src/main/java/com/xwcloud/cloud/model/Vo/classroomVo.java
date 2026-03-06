package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class classroomVo {
    @ApiModelProperty(value = "教室名称")
    private String classRoomName;
    @ApiModelProperty(value = "是否检测冲突")
    private Boolean ischongtu;
    private String id;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "录入员工姓名")
    private String recordInStaffName;
    @ApiModelProperty(value = "录入时间")
    private Date recordInTime;
}

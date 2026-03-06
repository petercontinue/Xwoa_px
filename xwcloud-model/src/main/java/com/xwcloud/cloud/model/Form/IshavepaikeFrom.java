package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class IshavepaikeFrom {
    @ApiModelProperty(value = "教师", required = true)
    private String teacher;
    @ApiModelProperty(value = "日期", required = true)
    private String haveclassDate;
    @ApiModelProperty(value = "开始时间", required = true)
    private String startLessonDateTime;
    @ApiModelProperty(value = "结束时间", required = true)
    private String endLessonDateTime;
    @ApiModelProperty(value = "班级ID", required = true)
    private Long classID;

}

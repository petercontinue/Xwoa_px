package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class pkchongtuVo {
    @ApiModelProperty(value = "开始上课时间")
    private Date startLessonDateTime;
    @ApiModelProperty(value = "结束上课时间")
    private Date endLessonDateTime;
    @ApiModelProperty(value = "班级ID")
    private Long classID;
    @ApiModelProperty(value = "教室ID")
    private Long classRooID;
    @ApiModelProperty(value = "有课日期")
    private Date haveClassDate;
    @ApiModelProperty(value = "教师ID")
    private Long teacherID;
    @ApiModelProperty(value = "课程ID")
    private Long kechengID;
}

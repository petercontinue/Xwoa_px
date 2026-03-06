package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.sql.Time;
import java.util.Date;

@Data
/**
 * 修改排课
 */
public class editpaikeFrom {
    @ApiModelProperty(value = "排课ID", required = true)
    private long paikeid;

    @ApiModelProperty(value = "修改学员数据", required = true)
    private String editStuData;

    @ApiModelProperty(value = "课程", required = true)
    private Long kecheng;

    @ApiModelProperty(value = "班级", required = true)
    private Long editclass;

    @ApiModelProperty(value = "上课老师", required = true)
    private String showjiaoshi;

    @ApiModelProperty(value = "教室ID", required = true)
    private Long editclassRoomID;

    @ApiModelProperty(value = "上课日期", required = true)
    private Date editenddate;

    @ApiModelProperty(value = "开始时间", required = true)
    private String editstarttime;

    @ApiModelProperty(value = "结束时间", required = true)
    private String editendtime;

    @ApiModelProperty(value = "是否检测排课冲突", required = true)
    private String checkPKchongtuEdit;
}

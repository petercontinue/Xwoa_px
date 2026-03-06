package com.xwcloud.cloud.model.Form;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
/**
 * 保存排课
 */
public class savepaikeFrom {
    @ApiModelProperty(value = "tabdata", required = true)
    private String tabdata;

    @ApiModelProperty(value = "排课学员信息", required = true)
    private String newStuData;

    @ApiModelProperty(value = "课程内容", required = true)
    private String kcContentCount;

    @ApiModelProperty(value = "课程", required = true)
    private Long kecheng;

    @ApiModelProperty(value = "班级", required = true)
    private Long classNum;

    @ApiModelProperty(value = "任课老师", required = true )
    private String showjiaoshi;

    @ApiModelProperty(value = "教室ID", required = true)
    private Long classRoomID;

    @ApiModelProperty(value = "是否检测排课冲突", required = false)
    private String checkPKchongtu;

}


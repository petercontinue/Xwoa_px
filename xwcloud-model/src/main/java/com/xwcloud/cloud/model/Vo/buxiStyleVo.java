package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//补习方式
@Data
public class buxiStyleVo {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "课程ID")
    private String kechengID;
    @ApiModelProperty(value = "学生ID")
    private String stuID;
    @ApiModelProperty(value = "是否是一对一")
    private int is1v1KC;
    @ApiModelProperty(value = "是否显示")
    private String isShow;

}

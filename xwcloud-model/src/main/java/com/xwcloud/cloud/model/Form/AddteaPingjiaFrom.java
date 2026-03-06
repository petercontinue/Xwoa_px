package com.xwcloud.cloud.model.Form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddteaPingjiaFrom {
    @ApiModelProperty(value = "排课ID", required = true)
    private String pkid;
    @ApiModelProperty(value = "学员ID", required = true)
    private String stuID;
    @ApiModelProperty(value = "图片url", required = true)
    private String imgurl;
    @ApiModelProperty(value = "yinpinurl", required = true)
    private String mp3url;
    @ApiModelProperty(value = "视频url", required = true)
    private String videourl;
    @ApiModelProperty(value = "评分数据", required = true)
    private String pfvalue;
    @ApiModelProperty(value = "总评", required = true)
    private String sturateshuoming;
    @ApiModelProperty(value = "评价上课学员老师ID", required = true)
    private String teacherID;
}

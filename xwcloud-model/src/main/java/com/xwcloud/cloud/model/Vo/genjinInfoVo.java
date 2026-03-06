package com.xwcloud.cloud.model.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class genjinInfoVo {
    @ApiModelProperty(value = "跟进数据ID")
    private String id;
    @ApiModelProperty(value = "跟进内容")
    private String gengjinText;
    @ApiModelProperty(value = "跟进时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gengjinTime;
    @ApiModelProperty(value = "跟进人姓名")
    private String addStaffName;
    @ApiModelProperty(value = "下次跟进时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date nextGenjinTime;
    @ApiModelProperty(value = "意向程度")
    private Long yxLevelID;
    private String telLevelName;
}

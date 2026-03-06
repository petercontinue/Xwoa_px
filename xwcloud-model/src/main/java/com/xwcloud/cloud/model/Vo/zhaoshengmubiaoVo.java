package com.xwcloud.cloud.model.Vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class zhaoshengmubiaoVo {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "校区ID")
    private String campusID;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "年月")
    private String yearMonth;
    @ApiModelProperty(value = "月业绩目标")
    private String monthMoney;
    @ApiModelProperty(value = "月人数目标")
    private String monthSum;
    @ApiModelProperty(value = "校区团队业绩分配")
    private String yuangong;
    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addtime;
}

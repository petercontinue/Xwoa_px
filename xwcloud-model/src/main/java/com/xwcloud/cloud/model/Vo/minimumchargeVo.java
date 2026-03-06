package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class minimumchargeVo {
    @ApiModelProperty(value = "数据ID")
    private String id;
    @ApiModelProperty(value = "补习方式名称")
    private String buxiStyleName;
    @ApiModelProperty(value = "年级名称")
    private String stuGradeName;
    @ApiModelProperty(value = "最低收费限制金额")
    private BigDecimal MinimumCharge;
    @ApiModelProperty(value = "添加人姓名")
    private String addStaffName;
}

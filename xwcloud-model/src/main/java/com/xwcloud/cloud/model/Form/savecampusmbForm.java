package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class savecampusmbForm {
    @ApiModelProperty(value = "员工ID", required = true)
    private Long staffID;
    @ApiModelProperty(value = "校区ID", required = true)
    private Long campusID;
    @ApiModelProperty(value = "目标年月", required = true)
    private String yearmonth;
    @ApiModelProperty(value = "月业绩目标", required = true)
    private BigDecimal monthMoney;
    @ApiModelProperty(value = "月人数目标", required = true)
    private Integer monthSum;
    @ApiModelProperty(value = "数据ID", required = false)
    private Long id;
    @ApiModelProperty(value = "企业ID", required = true)
    private String qiyeID;
}

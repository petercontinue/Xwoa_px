package com.xwcloud.cloud.model.Form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出库提交信息类
 */
@Data
public class outForm {
    @ApiModelProperty(value = "出库物品ID", required = true)
    private Long id;
    @ApiModelProperty(value = "出库数量", required = true)
    private BigDecimal outNum;
    @ApiModelProperty(value = "出库说明", required = true)
    private String outReason;
    @ApiModelProperty(value = "领用人ID", required = true)
    private Long lingyongStaffId;
    @ApiModelProperty(value = "经办人ID",required = true)
    private  long jibanStaffId;
    @ApiModelProperty(value = "出库日期", required = true)
    private String outDate;
}

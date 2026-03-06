package com.xwcloud.cloud.model.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class outjiluVo {
    @ApiModelProperty(value = "出库记录ID")
    private Long id;
    @ApiModelProperty(value = "校区名称")
    private String campusName;
    @ApiModelProperty(value = "物品名称")
    private String name;
    @ApiModelProperty(value = "物品类别名称")
    private String typeName;
    @ApiModelProperty(value = "物品规格")
    private String specs;
    @ApiModelProperty(value = "出库数量")
    private BigDecimal outNum;
    @ApiModelProperty(value = "数量单位")
    private String stockUnit;
    @ApiModelProperty(value = "出库说明")
    private String outReason;
    @ApiModelProperty(value = "出库日期")
    private String outDate;
    @ApiModelProperty(value = "录入人")
    private String staffName;
    @ApiModelProperty(value = "验收人")
    private String ystaffName;
    @ApiModelProperty(value = "出库前数量")
    private BigDecimal stockNum;

    private BigDecimal outafterNum;
    private  BigDecimal rukuafterNum;
}

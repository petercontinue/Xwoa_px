package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Data
public class addcaigoushenpiForm {
    @ApiModelProperty(value = "数据ID", required = false)
    private Long buyID;
    @ApiModelProperty(value = "校区ID", required = true)
    private Long campusID;
    @ApiModelProperty(value = "采购员工ID", required = true)
    private Long cgstaffName;
    @ApiModelProperty(value = "采购商品物品ID", required = true)
    private Long typeId;
    @ApiModelProperty(value = "采购商品名称", required = true)
    private String name;

    @ApiModelProperty(value = "采购商品规格", required = true)
    private String specs;

    @ApiModelProperty(value = "采购数量", required = true)
    private BigDecimal buyNum;

    @ApiModelProperty(value = "采购数量单位", required = true)
    private String stockUnit;
    @ApiModelProperty(value = "说明", required = true)
    private String reason;
    @ApiModelProperty(value = "申请时间", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String addDate;
}

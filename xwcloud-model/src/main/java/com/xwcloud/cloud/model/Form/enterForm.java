package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 入库信息
 */
@Data
public class enterForm {
    @ApiModelProperty(value = "校区ID",required = true)
    private Long campusID;
    private Long id;
    private String acceptStaffId;
    private String typeId;
    private String name;
    private String specs;
    private BigDecimal stockNum;
    private String stockUnit;
    private String addReason;
    private String addDate;
    private BigDecimal buyPrice;
    private BigDecimal salePrice;
    private String wupintiaoma;

}

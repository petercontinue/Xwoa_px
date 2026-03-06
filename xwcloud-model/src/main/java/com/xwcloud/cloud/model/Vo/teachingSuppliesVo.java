package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class teachingSuppliesVo {
    private long id;
    private Long campusID;
    private String name;
    private String typeName;
    private String specs;
    private String StockUnit;
    private String campusName;
    private String StockNum;
    private String typeId;
    private String addDate;
    private String outReason;
    private Long yanshouStaffId;
    private BigDecimal buyPrice;
    private BigDecimal salePrice;
    private boolean IsQiYong;
    private String changpinTiaoma;
    private String rukuShuoming;
}

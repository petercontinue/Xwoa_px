package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsBuyrecordsVo implements Serializable {

    private Long id;
    private String kehucompanyname;
    private int buySum;
    private BigDecimal price;
    private BigDecimal sumMoney;
    private String staffName;
    private Date buyTime;
    private Date addTime;
    private String shuoming;

}

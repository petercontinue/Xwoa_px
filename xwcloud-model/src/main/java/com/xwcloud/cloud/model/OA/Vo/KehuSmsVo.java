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
public class KehuSmsVo implements Serializable {

    private Long id;
    private Long qiyeID;
    private String kehucompanyname;
    private Integer smsRemain;
    private String kehucontractname;
    private String kehutelphone;
    private Integer buySum;
    private BigDecimal price;
    private BigDecimal sumMoney;
    private Date buyTime;


}

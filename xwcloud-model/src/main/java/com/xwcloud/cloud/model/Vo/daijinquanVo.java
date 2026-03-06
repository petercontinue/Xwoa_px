package com.xwcloud.cloud.model.Vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class daijinquanVo {
    private BigDecimal money;
    private Date creatTime;
    private String staffName;
}

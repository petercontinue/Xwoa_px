package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class buyWpVo {
    public Long id;
    public String wpName;
    public BigDecimal wpDanjia;
    public BigDecimal wpShuliang;
    public BigDecimal wpZongjia;
    public BigDecimal wpChushouJia;
}

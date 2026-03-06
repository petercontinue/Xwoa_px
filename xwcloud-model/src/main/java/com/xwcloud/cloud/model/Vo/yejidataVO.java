package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxmenutable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class yejidataVO {
    private String campusName;
    private BigDecimal yejiMoney;
    private List<String> DateList =new ArrayList<>();
    private List<BigDecimal> yejiData=new ArrayList<>();
    private String RgbColor;
}

package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class kechengListVo {
    private Long id;
    private String campusName;
    private String subjectName;
    private String kechengName;
    private Integer isShow;
    private Integer jifeiStyleID;
    private String buxiStyleName;
    private String classTimeStyleName;
    private BigDecimal kechengOriginalPrice;
    private BigDecimal kechengprice;
    private BigDecimal keshiNum;
    private BigDecimal buyZonjia;
    private boolean iskoukeshi;
    private int qingjiaTimes;
    private int kechengcontent;
    private String kechengImg;
    private String kechengbeizhu;
}

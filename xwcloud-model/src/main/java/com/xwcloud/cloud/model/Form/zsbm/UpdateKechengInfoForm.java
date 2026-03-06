package com.xwcloud.cloud.model.Form.zsbm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateKechengInfoForm {
    private String id;
    private String kechengName;
    private String subjectID;
    private String buxiStyleID;
    private String is1v1KC;
    private String classTimeStyleID;
    private BigDecimal kechengOriginalPrice;
    private BigDecimal kechengprice;
    private BigDecimal keshiNum;
    private BigDecimal buyZonjia;
    private String isShow;
    private String zsid;
    private String jifeiStyleID;
    private String campusID;
    private String bgColor;
    private String perdaysqj;
    private BigDecimal perkeshiqj;
    private String qingjiaTimes;
    private Boolean iskoukeshi;
    private String kechengImg;
    private String kechengbeizhu;
    private String kechengcontent;
    private String byMonthOrDay;
    private String showInApp;
}

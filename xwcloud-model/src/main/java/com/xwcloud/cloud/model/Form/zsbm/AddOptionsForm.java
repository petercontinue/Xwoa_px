package com.xwcloud.cloud.model.Form.zsbm;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddOptionsForm {
    private String kechengName;
    private String subjectID;
    private String buxiStyleID;
    private int is1v1KC;
    private String classTimeStyleID;
    private BigDecimal kechengOriginalPrice;
    private BigDecimal kechengprice;
    private BigDecimal keshiNum;
    private  BigDecimal buyZonjia;
    private int isShow;
    private String ZSid;
    private int jifeiStyleID;
    private String campusID;
    private String bgColor;
    private int perdaysqj;
    private BigDecimal perkeshiqj;
    private int qingjiaTimes;
    private Boolean iskoukeshi;
    private String kechengImg;
    private String kechengbeizhu;
    private String kechengcontent;
    private String showInApp;
    private String byMonthOrDay;
}

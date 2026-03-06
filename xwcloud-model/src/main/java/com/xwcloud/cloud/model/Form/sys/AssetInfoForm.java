package com.xwcloud.cloud.model.Form.sys;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetInfoForm {
    private String campusID;
    private String assetsName;
    private String leibie;
    private String guige;private BigDecimal num;
    private String danwei;
    private String addTime;
    private String addstaffID;
    private String beizhu;
}

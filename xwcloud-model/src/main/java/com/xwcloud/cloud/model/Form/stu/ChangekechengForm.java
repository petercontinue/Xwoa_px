package com.xwcloud.cloud.model.Form.stu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangekechengForm {
    private String hkbuxiID;
    private Boolean iszk;
    private String zk;
    private String oldkcMoney;
    private Long kcID;
    private Integer kcjifeistyleNew;
    private String hkStartDate;
    private String hkEndDate;
    private String hkKechengprice;
    private String hkKeshi;
}

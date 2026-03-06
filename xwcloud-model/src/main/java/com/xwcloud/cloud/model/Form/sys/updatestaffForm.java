package com.xwcloud.cloud.model.Form.sys;

import lombok.Data;

@Data
public class updatestaffForm {
    private String id;
    private Integer role;
    private Integer showInApp;
    private String staffName;
    private String staffTel;
    private String staffSex;
    private String campusID;
    private String staffPostID;
    private String joinTime;
    private String shuoMing;
    private String jiaoxueJingyan;
}

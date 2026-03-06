package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KehuVo implements Serializable {

    private Long id;
    private String kehucompanyname;
    private String kehucontractname;
    private Integer maxStuNum;
    private Integer campusnum;
    private String khShowJigouName;
    private String kehuinfobeizhu;
    private String kehuothertel;
    private String kehutelphone;
    private String hangyetypename;
    private String realName;
    private String yxname;
    private String yxFromName;

    private String yxnextgenjindatetime;
    private String weixin;

    private String staffName;
    private String areaname;
    private String lat;
    private String lng;
    private String parentid;
    private String shortname;

    private Date addTime;

    private String province;
    private String city;
    private Long provinceid;
    private Long cityid;

}

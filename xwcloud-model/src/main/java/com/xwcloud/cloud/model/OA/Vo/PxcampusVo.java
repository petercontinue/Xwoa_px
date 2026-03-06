package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PxcampusVo implements Serializable {

    private Long id;
    private String campusName;
    private String campusAddress;
    private String campusTel;
    private Integer ishaveMall;
    private Integer ishaveZhibo;
    private String QRcodePrint;
    private String QRcodeWx;
    private String accessToken;
    private String wxjiazhangADimg;
    private Boolean wxjiazhangIsShowShoplink;
    private String wxjiazhangShoplinkImg;
    private Integer isOpen;
    private Date buyDateTime;
    private Date nextPayTime;
    private String appID;
    private String appSecret;
    private String wxShanghuID;
    private String wxShanghuKey;
    private String wxShanghuZhengshuAddr;
    private Long qiyeID;
    private String kehucompanyname;

}

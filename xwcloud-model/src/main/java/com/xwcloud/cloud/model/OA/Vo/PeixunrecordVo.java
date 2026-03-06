package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeixunrecordVo implements Serializable {

    private Long id;
    private Long addstaffID;
    private String staffName;
    private String pxcontent;
    private Date addTime;
    private Date pxDate;
    private Long qiyeID;
    private String kehucompanyname;

}

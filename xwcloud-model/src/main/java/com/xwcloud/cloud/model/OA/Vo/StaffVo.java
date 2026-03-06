package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffVo implements Serializable {

    private Long id;
    private Date addtime;
    private String passwd;
    private String stafflogoimg;
    private String staffName;
    private Long staffpostID;
    private String staffpostName;
    private Integer staffstate;
    private String stafftel;
    private String kehucompanyname;
    private String shuoming;
    private String workName;
    private String worktel;
    private Date staffBirthday;
    private Date offtime;

}

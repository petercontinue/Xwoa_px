package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugOrAdviceInfo implements Serializable {

    private Long id;
    private Integer isBugOrAdvice;
    private String content;
    private Long qiyeID;
    private String kehucompanyname;
    private Date kehufankuiDateTime;
    private Long addUser;
    private String staffName;
    private Date addTime;

}

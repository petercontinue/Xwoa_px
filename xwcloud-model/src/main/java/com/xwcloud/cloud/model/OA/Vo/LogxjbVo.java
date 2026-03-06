package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogxjbVo implements Serializable {

    private Long id;
    private String systemContent;
    private String funcName;
    private Long staffID;
    private String staffName;
    private Long stuID;
    private String stuName;
    private int logType;
    private Date addTime;
    private String kehucompanyname;

}

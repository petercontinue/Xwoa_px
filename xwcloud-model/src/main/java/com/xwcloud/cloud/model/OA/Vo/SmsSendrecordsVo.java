package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsSendrecordsVo implements Serializable {

    private Long id;
    private String kehucompanyname;
    private String smscontent;
    private String smsPhone;
    private String shuoming;
    private Date sendTime;

}

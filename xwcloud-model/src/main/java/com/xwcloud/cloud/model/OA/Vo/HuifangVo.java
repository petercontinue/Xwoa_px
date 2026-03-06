package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HuifangVo implements Serializable {

    private Long id;
    private Date addTime;
    private String staffName;
    private String huifangContent;
    private String shuoming;
    private Date huifangDatetime;
    private String kehucompanyname;

}

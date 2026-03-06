package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YewuLiushuiVo implements Serializable {

    private Long id;
    private String kehucompanyname;
    private Integer liushuiType;
    private String liushuishuoming;
    private String staffName;
    private String addTime;

}

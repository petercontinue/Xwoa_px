package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * 空闲教室
 */
public class haveTimeTeaVo {
    private Long id;
    private String staffName;
    private String staffpostName;
    private Long campusID;
    private String campusName;
}

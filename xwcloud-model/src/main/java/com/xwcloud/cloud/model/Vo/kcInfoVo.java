package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
/**
 * （余额消课中有使用）
 * 获取科目课程
 */
public class kcInfoVo {
    private String id;//课程ID
    private String kechengName;//课程名称
    private String buxistyle;
    private String buxiStyleName;
    private String subjectName;
    private String text;//拼接好的课程名称
}

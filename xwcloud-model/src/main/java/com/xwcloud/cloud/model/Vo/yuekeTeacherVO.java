package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;
import lombok.Data;

@Data
public class yuekeTeacherVO extends Pxyueketeacherfabutable {
    private String campusName;
    private String kechengName;
    private String teacherName;
    private String className;
    private String buxiStyleName;
    private String current;// 当前人数
    private String state;// 约课状态
    private String kechengImg;

}

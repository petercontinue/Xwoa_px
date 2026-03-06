package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.sql.Time;

@Data
public class allxuankeVo {
    private String paikeID;
    private String classID;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private int keshi;
}

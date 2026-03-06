package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

@Data
public class tuixiankeVo {
    private Long id; //选课ID
    private Long paikeID; //排课ID
    private Date haveClassDate;
    private String startLessonDateTime;
    private String endLessonDateTime;
}

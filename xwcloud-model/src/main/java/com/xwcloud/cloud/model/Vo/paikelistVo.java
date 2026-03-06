package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Data
public class paikelistVo {
    private Long id;
    private Time startLessonDateTime;
    private Time endLessonDateTime;
    private Date haveClassDate;
    private String weekN;
    private Long classID;
    private Long buxiStyleID;
    private Long classTimeStyleID;
    private String classTimeStyleName;
    private BigDecimal kechengPrice;
    private String teacherIDs;
    private String teacherNames;
    private Long buxiID;
    private String className;
    private Long classRoomID;
    private String classRoomName;
    private Long kechengID;
    private String kechengName;
    private String xkstuName;
    private String tags;
    private String kechengContent;
    private long zhujiaoID;
    private Integer istuisongTixingMsg;
    private String huodongImg;
    private String huodongTitle;
    private Integer liulangtime;
    private Integer fenxiangtime;
    private String zixunphone;
    private BigDecimal shitingprice;
    private String kechengImg;
    private String kechengInfo;
    private String huodongshuoming;
}

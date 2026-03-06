package com.xwcloud.cloud.model.Form;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Data
public class shitingkeForm {
    private String startLessonDateTime;
    private String endLessonDateTime;
    private Date haveClassDate;
    private String teacherIDs;
    private String teacherNames;
    /**
     * 教室ID
     */
    private Long classRoomID;
    /**
     * 星期几
     */
    private String weekN;
    /**
     * 最大学员人数
     */
    private Integer maxStuNum;
    /**
     * 课程ID
     */
    private Long kechengID;
    /**
     * 试听课活动图片
     */
    private String huodongImg;
    /**
     * 试听课活动标题
     */
    private String huodongTitle;
    /**
     * 试听课活动说明
     */
    private String huodongshuoming;
    /**
     * 试听课咨询电话
     */
    private String zixunphone;

    /**
     * 试听价格
     */
    private BigDecimal shitingprice;

}

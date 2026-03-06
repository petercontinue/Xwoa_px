package com.xwcloud.cloud.model.Vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
//教师上课记录
public class TeaKehaoVo {
    private String id;
    private String campusName;//班级的校区
    private String TeachName;//上课教师
    private String zhujiao;//助教
    private String grade;//全部的年级
    private String kechengName;
    private String buxiStyleName;
    private String className;
    private Date haveClassDate;
    private Date startLessonDateTime;
    private Date endLessonDateTime;
    private BigDecimal keshiNum;
    private int ysStuNum;
    private int ssStuNum;
    private String shuoMing;
}

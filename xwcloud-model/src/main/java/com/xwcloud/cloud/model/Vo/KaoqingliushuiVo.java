package com.xwcloud.cloud.model.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Data
/**
 * 考勤流水
 */
public class KaoqingliushuiVo {
    private String id;
    private String campusName;
    private String zidingyiStuID;
    private String stuID;
    private String stuName;
    private String banzhuren;
    private String kechengName;
    private String tearch;//上课老师
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date haveclassDate;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time startClassDateTime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time endClassDateTime;
    private String kaoqingStyle;
}

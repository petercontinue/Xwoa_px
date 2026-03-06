package com.xwcloud.cloud.model.Vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Data
/**
 *
 */
public class tabdataVo {
    private int index;
    private String kechengContent;
    private String haveClassDate;
    private String startTime;
    private String endTime;
}
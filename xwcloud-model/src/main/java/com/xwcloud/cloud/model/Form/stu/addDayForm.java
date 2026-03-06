package com.xwcloud.cloud.model.Form.stu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class addDayForm {
    private Long songstuID;
    private int zhuansongtype;
    private Long songbuxikechengID;
    private String songYangyin;
    private BigDecimal skeshi;
    private Long shoukechengID;
    private String startDate;
    private String endDate;
}

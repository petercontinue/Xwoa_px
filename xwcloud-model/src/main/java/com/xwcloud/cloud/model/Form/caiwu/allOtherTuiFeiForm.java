package com.xwcloud.cloud.model.Form.caiwu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class allOtherTuiFeiForm {
    private String stuID;
    private String payStyleID;
    private String yejirenID;
    private String processingTime;
    private BigDecimal tuiallzfmoney;
    private String shuoming;
    private String qiandanIDs;
}

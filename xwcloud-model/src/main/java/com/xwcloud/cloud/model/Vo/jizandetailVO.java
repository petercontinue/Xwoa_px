package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class jizandetailVO {
    private String jizanHuodongName;
    private String jizanLogoUrl;
    private String jizanShuoming;
    private Date startTime;
    private Date endTime;
    private Date addTime;
    private List<jizanfaqiVO> faqilist=new ArrayList<>();
    private List<jizancanyuVO> helpzan=new ArrayList<>();

}

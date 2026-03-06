package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.WscKanjiaBangkanrecord;
import com.xwcloud.cloud.model.entity.WscKanjiaFaqirecord;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class kanjiadetailVO {
    private String huodongImg;
    private String huodongTitle;
    private String huodongshuoming;
    private Integer liulanTimes;
    private Integer fenxiangTimes;
    private String goodImg;
    private String goodsName;
    private BigDecimal buyPrice;
    private int faqicishu;
    private int canyucishu;
    private Date enddateTime;
    private BigDecimal kanjiaSuccessPrice;
    private BigDecimal kanjiaOniceMinNum;
    private BigDecimal kanjiaOniceMaxNum;
    private List<WscKanjiaFaqirecord> faqirecords = new ArrayList<>();
    private List<WscKanjiaBangkanrecord> bangkanList = new ArrayList<>();
    private List<WscKanjiaFaqirecord> kanjiasuccess=new ArrayList<>();
    /**
     * 是否已经发起了当前砍价活动
     */
    private int faqi;
    private  String shijianchuo;
    private long kanjiaID;
    private long goodsID;
    private long goodsTypeID;
}

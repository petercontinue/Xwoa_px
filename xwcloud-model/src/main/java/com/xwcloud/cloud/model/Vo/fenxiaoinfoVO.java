package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class fenxiaoinfoVO {
    private long id;
    private String nickName;
    private String headImage;
    private BigDecimal scRemainyongjin;
    private BigDecimal scWeijieYongjin;
    private BigDecimal scYijieYongjin;
    private BigDecimal scRemainMoney;
    private int alltuanduiCount;
    private int zhishuCount;
    private int feizhishuCount;
}

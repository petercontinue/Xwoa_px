package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistpricePingtuan;
import com.xwcloud.cloud.model.entity.WscPingtuanFaqirecord;
import com.xwcloud.cloud.model.entity.WscPingtuanJoinrecord;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class pingtuandetailVO {
    private List<WscGoodsshuxinglistpricePingtuan> ptjieti =new ArrayList<>();
    private String goodImg;
    private String goodsName;
    private BigDecimal buyPrice;
    private int kaituanshu;
    private int cantuanrenshu;
    private int huodongCount;
    private List<WscPingtuanFaqirecord> faqilist=new ArrayList<>();
    private List<WscPingtuanJoinrecord> joinlist=new ArrayList<>();
    private List<WscPingtuanFaqirecord> successlist=new ArrayList<>();
    private String shijianchuo;
    private Long goodsTypeID;
}

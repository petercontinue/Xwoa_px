package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class miaoshahuodongVO {
    private Date enddate;
    private String goodImg;
    private String goodsName;
    private BigDecimal buyPrice;
    private int miaoshacount;
    private int huodongCount;
    private int kucuncount;
    private List<miaoshachenggongVO> miaoshalist=new ArrayList<>();
    private String shijianchuo;
}

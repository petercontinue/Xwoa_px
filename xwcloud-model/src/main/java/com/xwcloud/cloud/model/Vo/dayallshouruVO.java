package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Data
public class dayallshouruVO {
    private String date;
    private HashMap<String, BigDecimal> data;
    private  List<Pxpaymoneystyletable> paymoneystyle;
}

package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class goodsInfoVO {
    private long id;
    private String guigeTypeName;
    private List<goodshuxingVO> children = new ArrayList<>();
}

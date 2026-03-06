package com.xwcloud.cloud.model.OA.Vo;


import com.xwcloud.cloud.model.entity.Pxcampustable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PxcampustableVo extends Pxcampustable implements Serializable {

    //加校区数量
    private Integer addCampusNum;
    private String liushuishuoming;

}

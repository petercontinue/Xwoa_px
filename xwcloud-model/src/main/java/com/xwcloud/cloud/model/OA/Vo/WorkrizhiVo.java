package com.xwcloud.cloud.model.OA.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkrizhiVo implements Serializable {

    private Long id;
    private String workHuibaoShuoming;
    private String workJihua;
    private Boolean readState;
    private int type;
    private Long huibaoToStaffID;
    private String huibaoTOStaffName;
    private String staffName;
    private Date addTime;

}

package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxzhaoshenmubiaostafftable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class zhaoshengStaffMubaoVo extends Pxzhaoshenmubiaostafftable {
    private Long campusID;
    private String staffName;
}

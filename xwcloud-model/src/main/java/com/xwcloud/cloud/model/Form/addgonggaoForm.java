package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class addgonggaoForm {
    @ApiModelProperty(value = "数据ID",required = false)
    private Long Id;
    @ApiModelProperty(value = "公告标题",required = true)
    private String gonggaoTitel;
    @ApiModelProperty(value = "公告内容",required = true)
    private String gonggaoText;
    @ApiModelProperty(value = "发送类型",required = true)
    private Integer sendType;
    @ApiModelProperty(value = "发送校区的数组")
    private String []sendCampusId;
    @ApiModelProperty(value = "校区ID（按照岗位发送时传入校区ID）")
    private Long campusID;
    @ApiModelProperty(value = "校区岗位ID")
    private String []sendStaffPostCampusId;
    @ApiModelProperty(value = "发送消息员工ID集合")
    private String []sendStaffId;
}

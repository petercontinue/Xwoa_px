package com.xwcloud.cloud.model.Form;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ChabanForm {
    @ApiModelProperty(value = "意向学员ID", required = true)
    private Long yxstuID;
    @ApiModelProperty(value = "校区ID", required = true)
    private Long campusID;
    @ApiModelProperty(value = "插班还是一对一", required = true)
    private Integer chabanOr1v1;
    @ApiModelProperty(value = "课程ID", required = true)
    private Long kechengID;
    @ApiModelProperty(value = "员工ID")
    private Long staffID;
    @ApiModelProperty(value = "试听记录ID")
    private Long shitingRecordsID;
    @ApiModelProperty(value = "排课ID")
    private Long paikeID;
    @ApiModelProperty(value = "试听班级")
    private Long StClass;
    @ApiModelProperty(value = "一对一试听老师ID")
    private Long shiting1v1staff;
    @ApiModelProperty(value = "试听日期")
    private Date stdate;
    @ApiModelProperty(value = "开始上课时间")
    private Date startdateTime;
    @ApiModelProperty(value = "结束上课时间")
    private Date enddateTime;
    @ApiModelProperty(value = "教室ID")
    private Long classRoomID;
    @ApiModelProperty(value = "是否检测冲突")
    private Integer checkCT;
    @ApiModelProperty(value = "企业ID")
    private Long qiyeID;
}

package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxstutable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PxStuTableVo extends Pxstutable {
    @ApiModelProperty("学生年级名字")
    private String stuGradeName;
    @ApiModelProperty("学生校区名字")
    private String campusName;
    @ApiModelProperty("来源途径")
    private String telFromName;
    @ApiModelProperty("意向程度")
    private String telLevelName;
    @ApiModelProperty("登记老师")
    private String dengjiTeacherName;
    @ApiModelProperty("市场老师")
    private String yxShichangTeacherName;
    @ApiModelProperty("负责老师")
    private String yxFenpeiName;
    @ApiModelProperty("电话关系")
    private String parentTelRelationValue;


}

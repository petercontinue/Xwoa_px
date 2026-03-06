package com.xwcloud.cloud.model.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AddgenjinForm {
    @ApiModelProperty(value = "跟进人ID", required = true)
    private Long staffID;
    @ApiModelProperty(value = "跟进学生ID", required = true)
    private Long stuID;
    @ApiModelProperty(value = "跟进时间", required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date genjinsdate;
    @ApiModelProperty(value = "下次跟进时间", required = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date nextgenjindate;
    @ApiModelProperty(value = "跟进说明", required = true)
    private String genjintext;
    @ApiModelProperty(value = "跟进数据ID，修改跟进记录时使用", required = false)
    private Long genjinID;
    @ApiModelProperty(value = "企业ID", required = true)
    private String qiyeID;
}

package com.xwcloud.cloud.model.Form;

import com.xwcloud.cloud.model.entity.Pxstuparamvaluetable;
import com.xwcloud.cloud.model.entity.Pxstutable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class YxStuVoForm extends Pxstutable {

    @ApiModelProperty("自定义字段")
    List<Pxstuparamvaluetable> diyParam;

}

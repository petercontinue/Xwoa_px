package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.common.ExcelColumn;
import lombok.Data;

@Data
public class zichanImportVo {
    @ExcelColumn(value = "校区(必填)", col = 1)
    private String campusName;
    @ExcelColumn(value = "名称(必填)", col = 2)
    private String articleName;
    @ExcelColumn(value = "类别(必填)", col = 3)
    private String leibie;
    @ExcelColumn(value = "规格(必填)", col = 4)
    private String guige;
    @ExcelColumn(value = "入库数量(必填)", col = 5)
    private String rukunum;
    @ExcelColumn(value = "单位(必填)", col = 6)
    private String danwei;
    @ExcelColumn(value = "入库说明(必填)", col = 7)
    private String rukushuoming;
    @ExcelColumn(value = "入库时间(必填)", col = 8)
    private String rukuDate;
    @ExcelColumn(value = "经办人(必填)", col = 9)
    private String jibanren;

}

package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.common.ExcelColumn;
import lombok.Data;

@Data
public class GongziImportVo {
    @ExcelColumn(value = "校区", col = 1)
    private String campusName;
    @ExcelColumn(value = "员工名称", col = 2)
    private String staffName;
    @ExcelColumn(value = "岗位", col = 3)
    private String staffPostName;
    @ExcelColumn(value = "工资开始时间", col = 4)
    private String GongziDate;
    @ExcelColumn(value = "工资结束时间", col = 5)
    private String GongziEndDate;

}

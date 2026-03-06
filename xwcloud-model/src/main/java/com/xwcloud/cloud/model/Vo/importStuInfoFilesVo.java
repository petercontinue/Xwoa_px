package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.common.ExcelColumn;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class importStuInfoFilesVo {
    @ExcelColumn(value = "学员姓名", col = 1)
    private String stuName;
    @ExcelColumn(value = "性别", col = 2)
    private String stuSex;
    @ExcelColumn(value = "联系电话（必填）", col = 3)
    private String parentTel;
    @ExcelColumn(value = "校区（必填）", col = 4)
    private String stuCampusName;
    @ExcelColumn(value = "年级（必填）", col = 5)
    private String stuGradeName;
    @ExcelColumn(value = "积分", col = 6)
    private BigDecimal stuIntegar;
    @ExcelColumn(value = "备注", col = 7)
    private String beizhu;
    @ExcelColumn(value = "生日", col = 8)
    private String stuBirthDay;
    @ExcelColumn(value = "联系电话关系", col = 9)
    private String parentRelation;
    @ExcelColumn(value = "学员积极性", col = 10)
    private String jijixing;
    @ExcelColumn(value = "学员性格", col = 11)
    private String xingge;
    @ExcelColumn(value = "老师要求", col = 12)
    private String laoshiyaoqiu;
    @ExcelColumn(value = "入学成绩", col = 13)
    private String ruxuechengji;
}

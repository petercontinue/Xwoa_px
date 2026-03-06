package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.common.ExcelColumn;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class importyixiangStuInfoFilesVo {
    @ExcelColumn(value = "学员姓名", col = 1)
    private String stuName;
    @ExcelColumn(value = "性别", col = 2)
    private String stuSex;
    @ExcelColumn(value = "联系电话（必填）", col = 3)
    private String parentTel;
    @ExcelColumn(value = "年级/年龄段", col = 4)
    private String stuGradeName;
    @ExcelColumn(value = "校区", col = 5)
    private String campusName;
    @ExcelColumn(value = "来源途径", col = 6)
    private String yixianglaiyuan;
    @ExcelColumn(value = "意向科目（以逗号隔开）", col = 7)
    private String yixiangkemu;
    @ExcelColumn(value = "意向程度", col = 8)
    private String yixiangchengdu;
    @ExcelColumn(value = "登记人", col = 9)
    private String dengjiTeacher;
    @ExcelColumn(value = "登记时间", col = 10)
    private String dengjiTime;
    @ExcelColumn(value = "负责人", col = 11)
    private String fuzeTeacher;
    @ExcelColumn(value = "数据来源人", col = 12)
    private String laiyuanTeacher;
    @ExcelColumn(value = "学员电话", col = 13)
    private String stuTel;
    @ExcelColumn(value = "学员生日", col = 14)
    private String stuBirthday;
}

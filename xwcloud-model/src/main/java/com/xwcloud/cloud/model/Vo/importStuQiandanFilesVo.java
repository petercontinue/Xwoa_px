package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.common.ExcelColumn;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class importStuQiandanFilesVo {
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
    @ExcelColumn(value = "新签或续费", col = 7)
    private String moneyStyle;
    @ExcelColumn(value = "培训科目", col = 8)
    private String kemuName;
    @ExcelColumn(value = "培训课程", col = 9)
    private String kechengName;
    @ExcelColumn(value = "培训方式", col = 10)
    private String buxiStyle;
    @ExcelColumn(value = "课程时长", col = 11)
    private String classTimeStyle;
    @ExcelColumn(value = "班级名称", col = 12)
    private String className;
    @ExcelColumn(value = "计费方式", col = 13)
    private String jifeiStyle;
    @ExcelColumn(value = "开始时间", col = 14)
    private String startDate;
    @ExcelColumn(value = "结束时间", col = 15)
    private String endDate;
    @ExcelColumn(value = "课程原单价", col = 16)
    private BigDecimal kechengOriginalPrice;
    @ExcelColumn(value = "课程实收单价", col = 17)
    private BigDecimal kechengprice;
    @ExcelColumn(value = "签单课时数", col = 18)
    private BigDecimal keshiNum;
    @ExcelColumn(value = "签单单课程总价", col = 19)
    private BigDecimal HetongMoney;
    @ExcelColumn(value = "代金券金额", col = 20)
    private BigDecimal daijinquanMoney;
    @ExcelColumn(value = "实收金额", col = 21)
    private BigDecimal shishouTotalMoney;
    @ExcelColumn(value = "签单时间", col = 22)
    private String qiandandate;
    @ExcelColumn(value = "额外赠送课时", col = 23)
    private BigDecimal zengsongkeshi;
    @ExcelColumn(value = "支付方式", col = 24)
    private String PayMoneyStyle;
    @ExcelColumn(value = "备注", col = 25)
    private String stuXuexi;
    @ExcelColumn(value = "出生日期", col = 26)
    private String stubirthday;
    @ExcelColumn(value = "联系电话关系", col = 27)
    private String parentRelation;
}

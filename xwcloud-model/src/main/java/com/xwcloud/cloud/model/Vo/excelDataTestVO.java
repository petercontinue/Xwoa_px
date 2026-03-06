package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.common.ExcelColumn;
import lombok.Data;

@Data
public class excelDataTestVO {
    @ExcelColumn(value = "校区", col = 1)
    private String campusName;
    @ExcelColumn(value = "名称", col = 2)
    private String bookName;
    @ExcelColumn(value = "总数量", col = 3)
    private String bookNumber;
    @ExcelColumn(value = "是否带答案侧", col = 4)
    private String ishaveAnser;
    @ExcelColumn(value = "是否带光盘", col = 5)
    private String ishaveguanpan;
    @ExcelColumn(value = "作者 （选填）", col = 6)
    private String auther;
    @ExcelColumn(value = "出版社（选填）", col = 7)
    private String chubanshe;
    @ExcelColumn(value = "出版时间 （选填）", col = 8)
    private String chubanDate;
    @ExcelColumn(value = "版次（选填）", col = 9)
    private String banci;
    @ExcelColumn(value = "ISBN（选填）", col = 10)
    private String ISBN;
    @ExcelColumn(value = "备注（选填）", col = 11)
    private String beizhu;

}

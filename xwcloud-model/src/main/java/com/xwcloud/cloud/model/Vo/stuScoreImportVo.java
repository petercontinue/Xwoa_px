package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.common.ExcelColumn;
import lombok.Data;

@Data
public class stuScoreImportVo {
    @ExcelColumn(value = "学员名字", col = 1)
    private String stuName;
    @ExcelColumn(value = "成绩类型", col = 2)
    private String scoreType;
    @ExcelColumn(value = "科目名称", col = 3)
    private String subjectName;
    @ExcelColumn(value = "课程名称", col = 3)
    private String kechengName;
    @ExcelColumn(value = "学员成绩", col = 4)
    private String score;
    @ExcelColumn(value = "单科排名", col = 5)
    private String dankepaiming;
    @ExcelColumn(value = "总分排名", col = 6)
    private String zongfenpaiming;
    @ExcelColumn(value = "考试类型", col = 7)
    private String testType;
    @ExcelColumn(value = "考试时间", col = 8)
    private String scoreDate;
    @ExcelColumn(value = "考试内容", col = 9)
    private String testTitle;
    @ExcelColumn(value = "备注", col = 10)
    private String beiZhu;
}

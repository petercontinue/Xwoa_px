package com.xwcloud.cloud.model.Vo;

import lombok.Data;

import java.util.Date;

//学员成绩
@Data
public class scoreVo {
    private String id;
    private String campusName;
    private String stuGradeName;
    private String stuName;
    private String banzhuren;//班主任
    private String subjectName;//科目
    private String testTitle;//测试内容
    private String score;//成绩
    private String kechengID;
    private String kechengName;
    private String dankepaiming;//单科排名
    private String zongfenpaiming;//总分排名
    private String testType;//考试类型
    private String testTypeID;
    private String scoreType;//成绩类型
    private Date scoreDate;//考试时间
    private Date addDateTime;//添加时间
    private String staffName;//经办人
    private String beiZhu;//备注
}

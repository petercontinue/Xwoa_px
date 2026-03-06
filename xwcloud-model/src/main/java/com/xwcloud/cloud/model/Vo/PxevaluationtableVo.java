package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxevaluationtable;

public class PxevaluationtableVo extends Pxevaluationtable {
    private String campusName;// 校区名称
    private String campusID; // 校区ID
    private String zidingyiStuID; // 学号
    private String stuName; // 学生姓名
    private String className; // 班级名称
    private String kechengName; // 课程名称
    private String staffName; // 教师姓名

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getCampusID() {
        return campusID;
    }

    public void setCampusID(String campusID) {
        this.campusID = campusID;
    }

    public String getZidingyiStuID() {
        return zidingyiStuID;
    }

    public void setZidingyiStuID(String zidingyiStuID) {
        this.zidingyiStuID = zidingyiStuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getKechengName() {
        return kechengName;
    }

    public void setKechengName(String kechengName) {
        this.kechengName = kechengName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}

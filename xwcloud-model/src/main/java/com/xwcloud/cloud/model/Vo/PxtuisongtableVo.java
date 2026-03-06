package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxtuisongtable;

public class PxtuisongtableVo extends Pxtuisongtable {
    private String campusName;// 校区名称
    private String campusID; // 校区ID
    private String zidingyiStuID; // 学号
    private String stuName; // 学生姓名
    private String staffName; // 班主任
    private String tuisongType; // 推送类型

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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getTuisongType() {
        return tuisongType;
    }

    public void setTuisongType(String tuisongType) {
        this.tuisongType = tuisongType;
    }
}

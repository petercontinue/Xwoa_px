package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxqingjiatable;

public class PxqingjiatableVo extends Pxqingjiatable {
    private String kechengName;// 课程名称
    private String className;// 班级名称
    private String stuName;// 请假学员名称
    private String staffName;// 班主任名称
    private String campusName;// 校区名称

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getKechengName() {
        return kechengName;
    }

    public void setKechengName(String kechengName) {
        this.kechengName = kechengName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

}

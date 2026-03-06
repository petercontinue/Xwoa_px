package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;

public class PxyueketeacherfabutableVo extends Pxyueketeacherfabutable {
    private String campusName;
    private String kechengName;
    private String teacherName;
    private String className;
    private String buxiStyleName;
    private String current;// 当前人数
    private String state;// 约课状态

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBuxiStyleName() {
        return buxiStyleName;
    }

    public void setBuxiStyleName(String buxiStyleName) {
        this.buxiStyleName = buxiStyleName;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}

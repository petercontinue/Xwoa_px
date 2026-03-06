package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxwxusertable;
import lombok.Data;

@Data
public class PxwxusertableVo extends Pxwxusertable {
    private String campusID;
    private String campusName;
    private String zidingyiStuID;
    private String stuName;
    private String banzhurenName;
    private String parentTel;
    private int activity;
    private String newstuID;
    private String wscName;
    private String stuTel;

    public String getStuID() {
        return newstuID;
    }

    public void setStuID(String newstuID) {
        this.newstuID = newstuID;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public String getCampusID() {
        return campusID;
    }

    public void setCampusID(String campusID) {
        this.campusID = campusID;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
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

    public String getBanzhurenName() {
        return banzhurenName;
    }

    public void setBanzhurenName(String banzhurenName) {
        this.banzhurenName = banzhurenName;
    }

    public String getParentTel() {
        return parentTel;
    }

    public void setParentTel(String parentTel) {
        this.parentTel = parentTel;
    }
}

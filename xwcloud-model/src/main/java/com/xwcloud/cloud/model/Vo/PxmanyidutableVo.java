package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxmanyidutable;

public class PxmanyidutableVo extends Pxmanyidutable {

    private String campusName;
    private String stuName;
    private String banzhurenName;
    private String parentTel;

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
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

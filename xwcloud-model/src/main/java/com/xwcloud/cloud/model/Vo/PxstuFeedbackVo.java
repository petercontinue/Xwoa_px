package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxtousutable;

public class PxstuFeedbackVo extends Pxtousutable {
    private String campusName;
    private String stuName;
    private String banzhurenName;
    private String stuGradeName;
    private String parentTel;
    private String chayueSatffName;

    public String getChayueSatffName() {
        return chayueSatffName;
    }

    public void setChayueSatffName(String chayueSatffName) {
        this.chayueSatffName = chayueSatffName;
    }

    public String getParentTel() {
        return parentTel;
    }

    public void setParentTel(String parentTel) {
        this.parentTel = parentTel;
    }

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

    public String getStuGradeName() {
        return stuGradeName;
    }

    public void setStuGradeName(String stuGradeName) {
        this.stuGradeName = stuGradeName;
    }
}

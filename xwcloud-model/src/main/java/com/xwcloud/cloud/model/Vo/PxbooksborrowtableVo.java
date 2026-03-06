package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxbooksborrowtable;

public class PxbooksborrowtableVo extends Pxbooksborrowtable {

    private String campusName;
    private String bookName;
    private String peopleName;
    private String surplusDays;
    private String dostaffName;
    private String returnNum;

    public String getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(String returnNum) {
        this.returnNum = returnNum;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getSurplusDays() {
        return surplusDays;
    }

    public void setSurplusDays(String surplusDays) {
        this.surplusDays = surplusDays;
    }

    public String getDostaffName() {
        return dostaffName;
    }

    public void setDostaffName(String dostaffName) {
        this.dostaffName = dostaffName;
    }


    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}

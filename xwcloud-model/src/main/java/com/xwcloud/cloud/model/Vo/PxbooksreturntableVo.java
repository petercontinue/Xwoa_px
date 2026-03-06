package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxbooksreturntable;

public class PxbooksreturntableVo extends Pxbooksreturntable {
    private String campusName; // 校区名称
    private String booksName; // 图书名称
    private String dostaffName; // 经办人
    private String peopleName;// 借书人

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getBooksName() {
        return booksName;
    }

    public void setBooksName(String booksName) {
        this.booksName = booksName;
    }

    public String getDostaffName() {
        return dostaffName;
    }

    public void setDostaffName(String dostaffName) {
        this.dostaffName = dostaffName;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
}

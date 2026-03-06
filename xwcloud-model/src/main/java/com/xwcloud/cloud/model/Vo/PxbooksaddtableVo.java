package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxbooksaddtable;

public class PxbooksaddtableVo extends Pxbooksaddtable {

    private String campusName;
    private String bookName;
    private String addstaffName;

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

    public String getAddstaffName() {
        return addstaffName;
    }

    public void setAddstaffName(String addstaffName) {
        this.addstaffName = addstaffName;
    }
}

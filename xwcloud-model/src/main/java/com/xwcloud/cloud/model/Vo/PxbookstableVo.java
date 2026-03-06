package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxbookstable;

public class PxbookstableVo extends Pxbookstable {
    private String campusName;
    private String alreadyNum;// 已经借出数量

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getAlreadyNum() {
        return alreadyNum;
    }

    public void setAlreadyNum(String alreadyNum) {
        this.alreadyNum = alreadyNum;
    }

}

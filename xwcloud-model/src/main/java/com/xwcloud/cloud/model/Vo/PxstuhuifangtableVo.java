package com.xwcloud.cloud.model.Vo;


import com.xwcloud.cloud.model.entity.Pxstuhuifangtable;

public class PxstuhuifangtableVo extends Pxstuhuifangtable {
    private String campusName;
    private String banzhurenName;
    private String huifangNum;

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getBanzhurenName() {
        return banzhurenName;
    }

    public void setBanzhurenName(String banzhurenName) {
        this.banzhurenName = banzhurenName;
    }

    public String getHuifangNum() {
        return huifangNum;
    }

    public void setHuifangNum(String huifangNum) {
        this.huifangNum = huifangNum;
    }
}

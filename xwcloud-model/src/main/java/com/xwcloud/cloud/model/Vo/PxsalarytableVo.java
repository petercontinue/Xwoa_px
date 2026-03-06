package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxsalarytable;

public class PxsalarytableVo extends Pxsalarytable {

    private String campusName; // 校区名称
    private String staffName; // 员工名称
    private String postName; // 岗位名称
    private String lururenName;// 录入人名称
    private String shengherenName; // 审核人名称
    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getLururenName() {
        return lururenName;
    }

    public void setLururenName(String lururenName) {
        this.lururenName = lururenName;
    }

    public String getShengherenName() {
        return shengherenName;
    }

    public void setShengherenName(String shengherenName) {
        this.shengherenName = shengherenName;
    }
}

package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxsalarystaffposttable;

public class PxsalarystaffposttableVo extends Pxsalarystaffposttable {

    private String staffpostName; // 岗位
    private String campusName; // 校区
    private String salaryStyle; // 工资项目
    private String sStyleID; // 工资项目ID
    private String campusID;// 校区ID
    private String postID; // 岗位ID

    public String getStaffpostName() {
        return staffpostName;
    }

    public void setStaffpostName(String staffpostName) {
        this.staffpostName = staffpostName;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getSalaryStyle() {
        return salaryStyle;
    }

    public void setSalaryStyle(String salaryStyle) {
        this.salaryStyle = salaryStyle;
    }

    public String getCampusID() {
        return campusID;
    }

    public void setCampusID(String campusID) {
        this.campusID = campusID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getsStyleID() {
        return sStyleID;
    }

    public void setsStyleID(String sStyleID) {
        this.sStyleID = sStyleID;
    }
}

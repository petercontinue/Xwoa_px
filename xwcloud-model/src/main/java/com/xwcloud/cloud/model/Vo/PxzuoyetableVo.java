package com.xwcloud.cloud.model.Vo;

import com.xwcloud.cloud.model.entity.Pxzuoyetable;

public class PxzuoyetableVo extends Pxzuoyetable {
    private String teacherName;
    private String className;
    private String submitDetails;// 交作业的人数,点击后链接到作业上交表

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubmitDetails() {
        return submitDetails;
    }

    public void setSubmitDetails(String submitDetails) {
        this.submitDetails = submitDetails;
    }
}

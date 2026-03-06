package com.xwcloud.cloud.model.Vo;

import lombok.Data;

@Data
public class mymobanVO {
    /**
     * 活动ID（whd_h5_huodongfabu表的ID）
     */
    private long hid;
    /**
     * 模板ID
     */
    private long mobanID;
    /**
     * 活动标题
     */
    private String huodongTitle;
    /**
     * 二维码路径
     */
    private String ewmUrl;
    /**
     * 模板图片路径
     */
    private String mbImgUrl;
    /**
     * 模板名称
     */
    private String mbName;
    /**
     * 是否发布
     */
    private Boolean isfabu;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 活动查看次数
     */
    private Integer lookNum;
    /**
     * 活动报名次数
     */
    private Integer bmNum;
    /**
     * 活动发布时间
     */
    private String fabuTime;
    /**
     * 活动结束时间
     */
    private String huodongEndDateTime;
    /**
     * 活动ID
     */
    private long typeID;
    /**
     * 活动名称
     */
    private String huodongName;
}

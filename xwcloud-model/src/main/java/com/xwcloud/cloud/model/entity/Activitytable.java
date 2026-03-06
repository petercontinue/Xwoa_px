package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-09
 */
@Data
@Accessors(chain = true)
@TableName("activitytable")
public class Activitytable extends Model<Activitytable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 微信分享出去时的标题
     */
	@TableField("activityName")
	private String activityName;
    /**
     * 微信分享出去时的内容
     */
	@TableField("activityDiscription")
	private String activityDiscription;
    /**
     * 分享出去的时候的LOGO图片
     */
	@TableField("logo")
	private String logo;
    /**
     * 轮播图
     */
	@TableField("Images")
	private String images;
    /**
     * 1 投票 2 砍价
     */
	@TableField("type")
	private Integer type;
    /**
     *  主办方
     */
	@TableField("sponsor")
	private String sponsor;
    /**
     * 是否使用
     */
	@TableField("isUser")
	private Boolean isUser;
    /**
     * 机构名称
     */
	@TableField("schoolName")
	private String schoolName;
    /**
     * 校区地址
     */
	@TableField("campusAdress")
	private String campusAdress;
    /**
     * 咨询热线
     */
	@TableField("lianxiTel")
	private String lianxiTel;
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Activitytable{" +
			", id=" + id +
			", activityName=" + activityName +
			", activityDiscription=" + activityDiscription +
			", logo=" + logo +
			", images=" + images +
			", type=" + type +
			", sponsor=" + sponsor +
			", isUser=" + isUser +
			", schoolName=" + schoolName +
			", campusAdress=" + campusAdress +
			", lianxiTel=" + lianxiTel +
			", qiyeID=" + qiyeID +
			"}";
	}
}

package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-20
 */
@Data
@Accessors(chain = true)
@TableName("whd_h5_moban")
public class WhdH5Moban extends Model<WhdH5Moban> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
    /**
     * 模板名称
     */
	@TableField("mbName")
	private String mbName;
    /**
     * 模板LOGO图URL
     */
	@TableField("mbImgUrl")
	private String mbImgUrl;
    /**
     * 二维码图URL
     */
	@TableField("ewmUrl")
	private String ewmUrl;
    /**
     * 模板类型ID，对应wsc_huodong表的id
     */
	@TableField("mbTypeID")
	private Long mbTypeID;
    /**
     * 活动名称的字体颜色
     */
	@TableField("headTxtColor")
	private String headTxtColor;
    /**
     * 活动名称文字的背景颜色
     */
	@TableField("headTxtBgcolor")
	private String headTxtBgcolor;
    /**
     * 下面正文内容的背景颜色
     */
	@TableField("contentBgcolor")
	private String contentBgcolor;
    /**
     * 二级标题文字的字体颜色
     */
	@TableField("xheadTxtColor")
	private String xheadTxtColor;
    /**
     * 二级标题的背景图片URL
     */
	@TableField("xheadImgUrl")
	private String xheadImgUrl;
    /**
     * 是否启用，1启用，2不启用，默认值1
     */
	@TableField("isOpen")
	private Integer isOpen;
    /**
     * 排序
     */
	@TableField("paixu")
	private Integer paixu;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WhdH5Moban{" +
			", id=" + id +
			", mbName=" + mbName +
			", mbImgUrl=" + mbImgUrl +
			", ewmUrl=" + ewmUrl +
			", mbTypeID=" + mbTypeID +
			", headTxtColor=" + headTxtColor +
			", headTxtBgcolor=" + headTxtBgcolor +
			", contentBgcolor=" + contentBgcolor +
			", xheadTxtColor=" + xheadTxtColor +
			", xheadImgUrl=" + xheadImgUrl +
			", isOpen=" + isOpen +
			", paixu=" + paixu +
			"}";
	}
}

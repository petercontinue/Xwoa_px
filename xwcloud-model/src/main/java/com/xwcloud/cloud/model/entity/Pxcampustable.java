package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-10
 */
@Data
@Accessors(chain = true)
@TableName("pxcampustable")
public class Pxcampustable extends Model<Pxcampustable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	private Long id;
	@TableField("campusName")
	private String campusName;
	@TableField("campusAddress")
	private String campusAddress;
	@TableField("campusTel")
	private String campusTel;
    /**
     * 是否有商城。0没有，1有，默认1
     */
	@TableField("ishaveMall")
	private Integer ishaveMall;
    /**
     * 是否开通直播，0没有，1有，默认0
     */
	@TableField("ishaveZhibo")
	private Integer ishaveZhibo;
    /**
     * 小票上打印显示的二维码
     */
	@TableField("QRcodePrint")
	private String qRcodePrint;
    /**
     * 微信公众号二维码
     */
	@TableField("QRcodeWx")
	private String qRcodeWx;
    /**
     * 微信accessToken
     */
	@TableField("accessToken")
	private String accessToken;
    /**
     * 微信家长端广告图banner
     */
	@TableField("wxjiazhangADimg")
	private String wxjiazhangADimg;
    /**
     * 微信学员端是否显示商城链接，默认true显示
     */
	@TableField("wxjiazhangIsShowShoplink")
	private Boolean wxjiazhangIsShowShoplink;
    /**
     * 学员微信端商城链接图片
     */
	@TableField("wxjiazhangShoplinkImg")
	private String wxjiazhangShoplinkImg;
    /**
     * 校区状态：1启用，2不启用，默认值1
     */
	@TableField("isOpen")
	private Integer isOpen;
    /**
     * 首次购买时间
     */
	@TableField("buyDateTime")
	private Date buyDateTime;
    /**
     * 下次付款时间
     */
	@TableField("nextPayTime")
	private Date nextPayTime;
    /**
     * 公众号appID
     */
	@TableField("appID")
	private String appID;
    /**
     * 公众号appSecret
     */
	@TableField("appSecret")
	private String appSecret;
    /**
     * 微信商户号ID
     */
	@TableField("wxShanghuID")
	private String wxShanghuID;
    /**
     * 微信商户号密钥
     */
	@TableField("wxShanghuKey")
	private String wxShanghuKey;
    /**
     * 微信商户号证书地址
     */
	@TableField("wxShanghuZhengshuAddr")
	private String wxShanghuZhengshuAddr;
    /**
     * 企业ID
     */
	@TableField("qiyeID")
	private Long qiyeID;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pxcampustable{" +
			", id=" + id +
			", campusName=" + campusName +
			", campusAddress=" + campusAddress +
			", campusTel=" + campusTel +
			", ishaveMall=" + ishaveMall +
			", ishaveZhibo=" + ishaveZhibo +
			", qRcodePrint=" + qRcodePrint +
			", qRcodeWx=" + qRcodeWx +
			", accessToken=" + accessToken +
			", wxjiazhangADimg=" + wxjiazhangADimg +
			", wxjiazhangIsShowShoplink=" + wxjiazhangIsShowShoplink +
			", wxjiazhangShoplinkImg=" + wxjiazhangShoplinkImg +
			", isOpen=" + isOpen +
			", buyDateTime=" + buyDateTime +
			", nextPayTime=" + nextPayTime +
			", appID=" + appID +
			", appSecret=" + appSecret +
			", wxShanghuID=" + wxShanghuID +
			", wxShanghuKey=" + wxShanghuKey +
			", wxShanghuZhengshuAddr=" + wxShanghuZhengshuAddr +
			", qiyeID=" + qiyeID +
			"}";
	}
}

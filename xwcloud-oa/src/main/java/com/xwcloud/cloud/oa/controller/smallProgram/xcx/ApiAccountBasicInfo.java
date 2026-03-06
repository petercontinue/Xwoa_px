package com.xwcloud.cloud.oa.controller.smallProgram.xcx;

import lombok.Data;

/**
 * @author
 * @date 2018/11/7
 */
@Data
public class ApiAccountBasicInfo {

	/**
	 *错误码
	 */
	private Integer errcode;
	/**
	 *错误信息
	 */
	private String errmsg;
	/**
	 *	帐号appid
	 */
	private String appid;
	/**
	 *帐号类型（1：订阅号，2：服务号，3：小程序）
	 */
	private Integer account_type;
	/**
	 *主体类型（1：企业）
	 */
	private Integer principal_type;
	/**
	 *主体名称
	 */
	private String principal_name;
	/**
	 *实名验证状态（1：实名验证成功，2：实名验证中，3：实名验证失败）调用接口1.1创建帐号时，
	 * realname_status会初始化为2对于注册方式为微信认证的帐号，资质认证成功时，
	 * realname_status会更新为1 注意！！！当realname_status不为1时，
	 * 帐号只允许调用本文档内的以下API：（即无权限调用其他API） 微信认证相关接口（参考2.x） 帐号设置相关接口（参考3.x）
	 */
	private Integer realname_status;
	/**
	 *微信认证信息
	 */
	private WxVerifyInfo wx_verify_info;
	/**
	 *功能介绍信息
	 */
	private SignatureInfo signature_info;
	/**
	 *头像信息
	 */
	private HeadImageInfo head_image_info;

	@Data
	public class WxVerifyInfo{
		/**
		 *是否资质认证（true：是，false：否）若是，拥有微信认证相关的权限。
		 */
		 private Integer qualification_verify;
		/**
		 *是否名称认证（true：是，false：否）对于公众号（订阅号、服务号），是名称认证，微信客户端才会有微信认证打勾的标识。
		 */
		 private Integer naming_verify;
		/**
		 *是否需要年审（true：是，false：否）（qualification_verify = true时才有该字段）
		 */
		private Integer annual_review;
		/**
		 *年审开始时间，时间戳（qualification_verify = true时才有该字段）
		 */
		private Long annual_review_begin_time;
		/**
		 *年审截止时间，时间戳（qualification_verify = true时才有该字段）
		 */
		private Long annual_review_end_time;


	}

	@Data
	public class SignatureInfo{
		/**
		 *功能介绍
		 */
		private String signature;
		/**
		 *功能介绍已使用修改次数（本月）
		 */
		private Integer modify_used_count;
		/**
		 *功能介绍修改次数总额度（本月）
		 */
		private Integer modify_quota;
	}

	@Data
	public class HeadImageInfo{
		/**
		 *头像url
		 */
		private String head_image_url;
		/**
		 *头像已使用修改次数（本月）
		 */
		private Integer modify_used_count;
		/**
		 *头像修改次数总额度（本月）
		 */
		private Integer modify_quota;
	}


}

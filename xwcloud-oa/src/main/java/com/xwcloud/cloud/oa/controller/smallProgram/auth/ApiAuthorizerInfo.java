package com.xwcloud.cloud.oa.controller.smallProgram.auth;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2018/11/6
 */
@Data
public class ApiAuthorizerInfo {

	/**
	 * 授权方昵称
	 */
	private String nickName;
	/**
	 * 授权方头像
	 */
	private String headImg;
	/**
	 * 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
	 */
	private Map<String, Integer> serviceTypeInfo;
	/**
	 * 	授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
	 */
	private Map<String, Integer> verifyTypeInfo;
	/**
	 * 授权方公众号的原始ID
	 */
	private String userName;
	/**
	 * 公众号的主体名称
	 */
	private String principalName;
	/**
	 * 用以了解以下功能的开通状况（0代表未开通，1代表已开通）： open_store:是否开通微信门店功能 open_scan:是否开通微信扫商品功能 open_pay:是否开通微信支付功能 open_card:是否开通微信卡券功能 open_shake:是否开通微信摇一摇功能
	 */
	private Map<String, Integer> businessInfo;
	/**
	 * 授权方公众号所设置的微信号，可能为空
	 */
	private String alias;
	/**
	 * 二维码图片的URL，开发者最好自行也进行保存
	 */
	private String qrcodeUl;

	/**
	 * 小程序
	 */

	/**
	 * 账号介绍
	 */
	private String signature;

	/**
	 * 可根据这个字段判断是否为小程序类型授权
	 */
	private MiniProgramInfo miniProgramInfo;

	@Data
	public class MiniProgramInfo {
		private Integer visitStatus;
		/**
		 * 小程序已设置的各个服务器域名
		 */
		private Network network;
		private List<Category> categories;

		@Data
		public class Category {
			private String first;
			private String second;
		}

		@Data
		public class Network {
			private List<String> requestDomain;
			private List<String> wsRequestDomain;
			private List<String> uploadDomain;
			private List<String> downloadDomain;
			private List<String> bizDomain;
		}
	}
}

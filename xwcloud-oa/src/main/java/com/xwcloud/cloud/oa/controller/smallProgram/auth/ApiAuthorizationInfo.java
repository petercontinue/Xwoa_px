package com.xwcloud.cloud.oa.controller.smallProgram.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author
 * @date 2018/11/6
 * 授权信息
 */
@Data
@NoArgsConstructor
public class ApiAuthorizationInfo {

	/**
	 * 授权方appid
	 */
	private String authorization_appid;

	private String authorizer_refresh_token;
	/**
	 * 	公众号授权给开发者的权限集列表，ID为1到15时分别代表： 1.消息管理权限 2.用户管理权限 3.帐号服务权限 4.网页服务权限 5.微信小店权限 6.微信多客服权限 7.群发与通知权限 8.微信卡券权限 9.微信扫一扫权限 10.微信连WIFI权限 11.素材管理权限 12.微信摇周边权限 13.微信门店权限 14.微信支付权限 15.自定义菜单权限 请注意： 1）该字段的返回不会考虑公众号是否具备该权限集的权限（因为可能部分具备），请根据公众号的帐号类型和认证情况，来判断公众号的接口权限。
	 */
	private List<ApiQueryAuthInfo.AuthorizationInfo.FuncInfo> func_info;

	/*@Data
	public class FuncInfo {
		private FuncscopeCategory funcscope_category;

		@Data
		public class FuncscopeCategory {
			private String id;
		}
	}*/
}

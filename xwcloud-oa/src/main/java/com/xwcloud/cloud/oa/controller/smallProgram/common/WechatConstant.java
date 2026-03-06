package com.xwcloud.cloud.oa.controller.smallProgram.common;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author
 * @date 2018/12/7
 */
public class WechatConstant {

	public static final String COMPONENT_VERIFY_TICKET_KEY = "wechat_component_verify_ticket";
	public static final String COMPONENT_ACCESS_TOKEN_KEY = "wechat_component_access_token";
	public static final String PRE_AUTH_CODE_KEY = "wechat_pre_auth_code";
	public static final String AUTHORIZER_ACCESS_TOKEN_KEY = "wechat_authorizer_access_token";
	public static final String AUTHORIZER_REFRESH_TOKEN_KEY = "wechat_authorizer_refresh_token";
	public static final String JSAPI_TICKET_KEY = "wechat_jsapi_ticket:";
	public static final String CARD_API_TICKET_KEY = "wechat_card_api_ticket:";
	/** 发生以下情况时尝试刷新access_token
	 * 40001 获取access_token时AppSecret错误，或者access_token无效
	 * 42001 access_token超时
	 * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
	 * */
	public static final List<Integer> ACCESS_TOKEN_ERRCODE_LIST = Lists.newArrayList(42001,40001,40014);



	/**
	 * 微信第三方授权API
	 */
	/**
	 * 2、获取第三方平台component_access_token
	 */
	public static final String API_COMPONENT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
	/**
	 * 3、获取预授权码pre_auth_code
	 */
	public static String API_CREATE_PREAUTHCODE_URL = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode";
	/**
	 * 授权注册页面扫码授权
	 */
	public static String COMPONENT_LOGIN_PAGE_URL = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s";
	/**
	 * 4、使用授权码换取公众号或小程序的接口调用凭据和授权信息
	 */
	public static String API_QUERY_AUTH_URL = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth";
	/**
	 *客服接口地址
	 */
	public static String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	/**
	 * 5、获取（刷新）授权公众号的令牌
	 */
	public static String API_AUTHORIZER_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token";
	/**
	 * 6、获取授权方的帐号基本信息
	 */
	public static String API_GET_AUTHORIZER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info";

	/**
	 * 7、获取授权方的选项设置信息
	 */
	public static String API_GET_AUTHORIZER_OPTION_URL = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_option";
	/**
	 * 8、设置授权方的选项信息
	 */
	public static String API_SET_AUTHORIZER_OPTION_URL = "https://api.weixin.qq.com/cgi-bin/component/api_set_authorizer_option";


	/**
	 * 代小程序实现业务
	 */

	/**
	 * 代小程序实现业务
	 * <p>
	 * 小程序代码模版库管理：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1506504150_nMMh6&token=&lang=zh_CN
	 * access_token 为 component_access_token
	 */
	public static String GET_TEMPLATE_DRAFT_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatedraftlist";
	public static String GET_TEMPLATE_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatelist";
	public static String ADD_TO_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/addtotemplate";
	public static String DELETE_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/deletetemplate";


	/**
	 * 设置小程序服务器域名
	 *
	 * <pre>
	 *     授权给第三方的小程序，其服务器域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的服务器域名将被删除，
	 *     只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加第三方自身的域名。
	 *     提示：需要先将域名登记到第三方平台的小程序服务器域名中，才可以调用接口进行配置
	 * </pre>
	 */
	public static String API_MODIFY_DOMAIN = "https://api.weixin.qq.com/wxa/modify_domain";
	/**
	 * 设置小程序业务域名（仅供第三方代小程序调用）
	 * <pre>
	 *     授权给第三方的小程序，其业务域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的业务域名将被删除，
	 *     只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。
	 * 提示：
	 * 1、需要先将域名登记到第三方平台的小程序业务域名中，才可以调用接口进行配置。
	 * 2、为授权的小程序配置域名时支持配置子域名，例如第三方登记的业务域名如为qq.com，则可以直接将qq.com及其子域名（如xxx.qq.com）也配置到授权的小程序中。
	 * </pre>
	 */
	public static String API_SET_WEBVIEW_DOMAIN = "https://api.weixin.qq.com/wxa/setwebviewdomain";
	/**
	 * 获取帐号基本信息
	 * <pre>
	 * GET请求
	 * 注意：需要使用1.3环节获取到的新创建小程序appid及authorization_code换取authorizer_refresh_token进而得到authorizer_access_token。
	 * </pre>
	 */
	public static String API_GET_ACCOUNT_BASICINFO = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo";
	/**
	 * 绑定微信用户为小程序体验者
	 */
	public static String API_BIND_TESTER = "https://api.weixin.qq.com/wxa/bind_tester";
	/**
	 * 解除绑定小程序的体验者
	 */
	public static String API_UNBIND_TESTER = "https://api.weixin.qq.com/wxa/unbind_tester";
	/**
	 * 获取体验者列表
	 */
	public static String API_GET_TESTERLIST = "https://api.weixin.qq.com/wxa/memberauth";
	/**
	 * 1. 为授权的小程序帐号上传小程序代码
	 */
	public static String API_CODE_COMMIT = "https://api.weixin.qq.com/wxa/commit";
	/**
	 * 2. 获取体验小程序的体验二维码
	 */
	public static String API_TEST_QRCODE = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=ACCESS_TOKEN";
	/**
	 * 3. 获取授权小程序帐号的可选类目
	 */
	public static String API_GET_CATEGORY = "https://api.weixin.qq.com/wxa/get_category";
	/**
	 * 4. 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
	 */
	public static String API_GET_PAGE = "https://api.weixin.qq.com/wxa/get_page";
	/**
	 * 5. 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
	 */
	public static String API_SUBMIT_AUDIT = "https://api.weixin.qq.com/wxa/submit_audit";
	/**
	 * 7. 查询某个指定版本的审核状态（仅供第三方代小程序调用）
	 */
	public static String API_GET_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_auditstatus";
	/**
	 * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
	 */
	public static String API_GET_LATEST_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_latest_auditstatus";
	/**
	 * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
	 */
	public static String API_RELEASE = "https://api.weixin.qq.com/wxa/release";
	/**
	 * 10. 修改小程序线上代码的可见状态（仅供第三方代小程序调用)
	 */
	public static String API_CHANGE_VISITSTATUS = "https://api.weixin.qq.com/wxa/change_visitstatus?access_token=ACCESS_TOKEN";

	/**
	 * 11.小程序版本回退（仅供第三方代小程序调用）
	 */
	public static String API_REVERT_CODE_RELEASE = "https://api.weixin.qq.com/cgi-bin/wxopen/getweappsupportversion";
	/**
	 * 12.查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
	 */
	public static String API_GET_WEAPP_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/getweappsupportversion?access_token=ACCESS_TOKEN";
	/**
	 * 13.设置最低基础库版本（仅供第三方代小程序调用）
	 */
	public static String API_SET_WEAPP_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/setweappsupportversion?access_token=ACCESS_TOKEN";

	/**
	 * 14.设置小程序“扫普通链接二维码打开小程序”能力
	 * <p>
	 *     TODO 暂时不实现
	 * </p>
	 */
	/**
	 * 15.小程序审核撤回
	 * <p>
	 * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
	 * </p>
	 */
	public static String API_UNDO_CODE_AUDIT = "https://api.weixin.qq.com/wxa/undocodeaudit";

	/**
	 * 16.1 小程序分阶段发布-分阶段发布接口
	 */
	public static String API_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/grayrelease?access_token=ACCESS_TOKEN";

	/**
	 * 16.2 小程序分阶段发布-取消分阶段发布
	 */
	public static String API_REVERT_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/revertgrayrelease?access_token=ACCESS_TOKEN";

	/**
	 * 16.3 小程序分阶段发布-查询当前分阶段发布详情
	 */
	public static String API_GET_GRAY_RELEASE_PLAN = "https://api.weixin.qq.com/wxa/getgrayreleaseplan?access_token=ACCESS_TOKEN";

	public static String MINIAPP_JSCODE_2_SESSION = "https://api.weixin.qq.com/sns/component/jscode2session?appid=%s&js_code=%s&grant_type=authorization_code&component_appid=%s";




}

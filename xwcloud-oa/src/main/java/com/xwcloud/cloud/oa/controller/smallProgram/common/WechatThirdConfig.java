package com.xwcloud.cloud.oa.controller.smallProgram.common;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author
 * @date 2018/12/4
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat.third")
public class WechatThirdConfig {

	/**
	 * 设置微信三方平台的appid
	 */
	private String componentAppId;

	/**
	 * 设置微信三方平台的app secret
	 */
	private String componentSecret;

	/**
	 * 设置微信三方平台的token
	 */
	private String componentToken;

	/**
	 * 设置微信三方平台的EncodingAESKey
	 */
	private String componentAesKey;

	private List<String> webViewDomain;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}

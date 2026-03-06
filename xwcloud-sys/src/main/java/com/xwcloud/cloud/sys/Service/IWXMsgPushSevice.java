package com.xwcloud.cloud.sys.Service;

import com.xwcloud.cloud.common.Message.TemplateParam;

import java.util.List;

public interface IWXMsgPushSevice {
    boolean wxMsgConsumptionSuccess(String openid, String templateId, String url, List<TemplateParam> paras);
}

package com.xwcloud.cloud.sys.Service.impl;


import com.xwcloud.cloud.common.Message.TemplateParam;
import com.xwcloud.cloud.common.Message.WXTmplMsgUtils;
import com.xwcloud.cloud.sys.Service.IWXMsgPushSevice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WXMsgPushServiceImpl implements IWXMsgPushSevice {
    @Override
    public boolean wxMsgConsumptionSuccess(String openid,String templateId,String url,List<TemplateParam> paras ) {
        return WXTmplMsgUtils.sendWXTmplMsg(templateId, openid, url , paras);
    }
}

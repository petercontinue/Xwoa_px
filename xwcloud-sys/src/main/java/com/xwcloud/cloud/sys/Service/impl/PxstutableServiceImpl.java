package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.common.Httprequests;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.sys.Dao.IPxstutableDao;
import com.xwcloud.cloud.sys.Service.IPxstutableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-18
 */
@Service
//@DS("#header.DBname")
public class PxstutableServiceImpl extends ServiceImpl<IPxstutableDao, Pxstutable> implements IPxstutableService {

    @Autowired
    IPxstutableDao iPxstutableDao;

    @Override
    public Pxstutable GetstuInfoByParentTel(String parentTel) {
        return iPxstutableDao.GetstuInfoByParentTel(parentTel);
    }

    @Override
    public String loginByWeixin(String code, String encryptedData, String iv) {
        Map<String, Object> map = new HashMap<String, Object>();
        //发送    https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code 获取用户的openid和session_key
        //注意这个是 WeChatTool.wxspAppid 是微信小程序的appid 从微信小程序后台获取 WeChatTool.wxspSecret 这个也一样，我这里是用了常量来进行保存方便多次使用
        String params = "appid=wxca12aabb9d440265&secret=6d385686fedfe8c8dea57de9c87a2e1e&js_code=" + code + "&grant_type=authorization_code";
        String sendGet = Httprequests.sendGet("https://api.weixin.qq.com/sns/jscode2session", params); //发起请求拿到key和openid
        return sendGet;
    }

    @Override
    public List<HashMap<String,String>> getlongtimenokeshiStu(Long qiyeID) {
        return iPxstutableDao.getlongtimenokeshiStu(qiyeID);
    }

    @Override
    public List<HashMap<String, String>> getwxNoUserList(QueryWrapper queryWrapper) {
        return iPxstutableDao.getwxNoUserList(queryWrapper);
    }
}

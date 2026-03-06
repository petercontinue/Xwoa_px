package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Service.IOaKehuService;

import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.wsc.Dao.IOaKehuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-06
 */
@Service
public class OaKehuServiceImpl extends ServiceImpl<IOaKehuDao, OaKehu> implements IOaKehuService {

    @Autowired
    IOaKehuDao iOaKehuDao;

    @Override
    public List<OaKehu> GetPhoneIncloudJigouStu(String parentTel) {
        return iOaKehuDao.GetPhoneIncloudJigouStu(parentTel);
    }

    @Override
    public List<OaKehu> GetPhoneIncloudjigouStaff(String staffTel) {
        return iOaKehuDao.GetPhoneIncloudjigouStaff(staffTel);
    }
}

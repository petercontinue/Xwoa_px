package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.sys.Dao.IOaKehuDao;
import com.xwcloud.cloud.sys.Service.IOaKehuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-21
 */
@Service
public class OaKehuServiceImpl extends ServiceImpl<IOaKehuDao, OaKehu> implements IOaKehuService {

    @Autowired
    IOaKehuDao iOaKehuDao;

    @Override
    public List<listVo> GetAlljigouByPhoneNumber(String PhoneNumber) {
        return iOaKehuDao.GetAlljigouByPhoneNumber(PhoneNumber);
    }

    @Override
    public List<listVo> GetAlljigouByStuPhoneNumber(String PhoneNumber) {
        return iOaKehuDao.GetAlljigouByStuPhoneNumber(PhoneNumber);
    }
}

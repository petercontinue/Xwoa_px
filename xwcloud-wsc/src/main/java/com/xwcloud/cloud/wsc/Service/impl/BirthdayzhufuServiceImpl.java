package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Service.IBirthdayzhufuService;

import com.xwcloud.cloud.model.Vo.birthdayzhufuVO;
import com.xwcloud.cloud.model.Vo.birthinfoVO;
import com.xwcloud.cloud.model.Vo.dianzangVO;
import com.xwcloud.cloud.model.entity.Birthdayzhufu;
import com.xwcloud.cloud.wsc.Dao.IBirthdayzhufuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-18
 */
@Service
public class BirthdayzhufuServiceImpl extends ServiceImpl<IBirthdayzhufuDao, Birthdayzhufu> implements IBirthdayzhufuService {

    @Autowired
    IBirthdayzhufuDao iBirthdayzhufuDao;

    @Override
    public List<dianzangVO> GetbirthDianzanInfo(long beidianzanUserID) {
        return iBirthdayzhufuDao.GetbirthDianzanInfo(beidianzanUserID);
    }

    @Override
    public List<birthdayzhufuVO> GetAllbirthdayZhufu(long zhufuUserID) {
        return iBirthdayzhufuDao.GetAllbirthdayZhufu(zhufuUserID);
    }

    @Override
    public List<birthinfoVO> GetshengriStuInfo(long stuID) {
        return iBirthdayzhufuDao.GetshengriStuInfo(stuID);
    }
}

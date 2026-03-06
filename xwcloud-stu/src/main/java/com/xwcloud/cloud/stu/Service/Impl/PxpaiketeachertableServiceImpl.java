package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.paiketeacherVo;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;
import com.xwcloud.cloud.stu.Dao.IPxpaiketeachertableDao;
import com.xwcloud.cloud.stu.Service.IPxpaiketeachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxpaiketeachertableServiceImpl extends ServiceImpl<IPxpaiketeachertableDao, Pxpaiketeachertable> implements IPxpaiketeachertableService {

    @Autowired
    IPxpaiketeachertableDao iPxpaiketeachertableDao;

    @Override
    public paiketeacherVo getPtID(Long paikeID, Long qiyeID) {
        return iPxpaiketeachertableDao.getPtID(paikeID, qiyeID);
    }

    @Override
    public paiketeacherVo getTkTpaike(Long paikeID, Long qiyeID) {
        return iPxpaiketeachertableDao.getTkTpaike(paikeID, qiyeID);
    }

    @Override
    public List<Pxpaiketeachertable> getBypaikeID(Long paikeID, Long qiyeID) {
        return iPxpaiketeachertableDao.getBypaikeID(paikeID, qiyeID);
    }

}

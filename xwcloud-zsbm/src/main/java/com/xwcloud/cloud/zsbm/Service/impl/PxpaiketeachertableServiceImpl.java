package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;
import com.xwcloud.cloud.zsbm.Dao.IPxpaiketeachertableDao;
import com.xwcloud.cloud.zsbm.Service.IPxpaiketeachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-17
 */
@Service
public class PxpaiketeachertableServiceImpl extends ServiceImpl<IPxpaiketeachertableDao, Pxpaiketeachertable> implements IPxpaiketeachertableService {

    @Autowired
    IPxpaiketeachertableDao iPxpaiketeachertableDao;

    @Override
    public int DeletePaikeTeacherByPaikeID(Long paikeID) {
        return iPxpaiketeachertableDao.DeletePaikeTeacherByPaikeID(paikeID);
    }


    @Override
    public List<Pxpaiketeachertable> DeleteAllPaikeTeacherBypaikeID(Long paikeID) {
        return iPxpaiketeachertableDao.DeleteAllPaikeTeacherBypaikeID(paikeID);
    }

    @Override
    public List<Pxpaiketeachertable> getPaiketeacherByPaikeID(Long paikeID) {
        return iPxpaiketeachertableDao.getPaiketeacherByPaikeID(paikeID);
    }
}

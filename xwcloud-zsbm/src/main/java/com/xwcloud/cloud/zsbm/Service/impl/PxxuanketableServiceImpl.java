package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
import com.xwcloud.cloud.zsbm.Dao.IPxxuanketableDao;
import com.xwcloud.cloud.zsbm.Service.IPxxuanketableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
@Service
public class PxxuanketableServiceImpl extends ServiceImpl<IPxxuanketableDao, Pxxuanketable> implements IPxxuanketableService {

    @Autowired
    IPxxuanketableDao iPxxuanketableDao;

    @Override
    public List<Pxxuanketable> GetXuankeByBuxiIDAndPaikeId(Long stuID, Long paikeID) {
        return iPxxuanketableDao.GetXuankeByBuxiIDAndPaikeId(stuID, paikeID);
    }

    @Override
    public int deleteXuanKeTable(Long paikeID) {
        return iPxxuanketableDao.deleteXuanKeTable(paikeID);
    }

    @Override
    public int deleteXuankeByBxIDAndStuID(Long bxID, Long stuID) {
        return iPxxuanketableDao.deleteXuankeByBxIDAndStuID(bxID, stuID);
    }

    @Override
    public List<Pxxuanketable> getAllXuankeByPaikeID(Long paikeID) {
        return iPxxuanketableDao.getAllXuankeByPaikeID(paikeID);
    }

    @Override
    public List<Pxxuanketable> GetAllXuankeBypkIDAndStuIDList(Long paikeID, Long stuID) {
        return iPxxuanketableDao.GetAllXuankeBypkIDAndStuIDList(paikeID, stuID);
    }

    @Override
    public int DeleteXuankeReords(Long paikeID, Long stuID) {
        return iPxxuanketableDao.DeleteXuankeReords(paikeID, stuID);
    }

    @Override
    public List<Pxxuanketable> GetXuankeByKehcnegID(Long kechengID) {
        return iPxxuanketableDao.GetXuankeByKehcnegID(kechengID);
    }

    @Override
    public List<Pxxuanketable> GetXuankeByPaikeID(Long PaikeID) {
        return iPxxuanketableDao.getAllXuankeByPaikeID(PaikeID);
    }
}

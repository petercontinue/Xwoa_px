package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.zsbm.Dao.IPxkeshistutableDao;
import com.xwcloud.cloud.zsbm.Service.IPxkeshistutableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
@Service
public class PxkeshistutableServiceImpl extends ServiceImpl<IPxkeshistutableDao, Pxkeshistutable> implements IPxkeshistutableService {

    @Autowired
    IPxkeshistutableDao iPxkeshistutableDao;

    @Override
    public List<Pxkeshistutable> getAllStuKeshiByStuID(Long stuID) {
        return iPxkeshistutableDao.getAllStuKeshiByStuID(stuID);
    }

    @Override
    public List<Pxkeshistutable> GetKehaostuByBuxikechengID(Long buxikechengID) {
        return iPxkeshistutableDao.GetKehaostuByBuxikechengID(buxikechengID);
    }

    @Override
    public List<Pxkeshistutable> GetKeshiStuList(long stuID) {
        return iPxkeshistutableDao.GetKeshiStuList(stuID);
    }
}

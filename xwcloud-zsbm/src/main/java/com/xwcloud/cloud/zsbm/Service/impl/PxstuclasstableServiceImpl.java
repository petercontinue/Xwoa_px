package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;
import com.xwcloud.cloud.zsbm.Dao.IPxstuclasstableDao;
import com.xwcloud.cloud.zsbm.Service.IPxstuclasstableService;
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
public class PxstuclasstableServiceImpl extends ServiceImpl<IPxstuclasstableDao, Pxstuclasstable> implements IPxstuclasstableService {

    @Autowired
    IPxstuclasstableDao iPxstuclasstableDao;

    @Override
    public Pxstuclasstable GetStuClassByBxIDAndClassID(Long classID, Long buxiID) {
        return iPxstuclasstableDao.GetStuClassByBxIDAndClassID(classID, buxiID);
    }

    @Override
    public Pxstuclasstable GetStuclassBybuxiID(Long buxiID) {
        return iPxstuclasstableDao.GetStuclassBybuxiID(buxiID);
    }

    @Override
    public List<Pxstuclasstable> GetStuclassBybuxiIDlist(Long buxiID) {
        return iPxstuclasstableDao.GetStuclassBybuxiIDlist(buxiID);
    }

    @Override
    public int DeleteStuclassbybuxiID(Long buxiID) {
        return iPxstuclasstableDao.DeleteStuclassbybuxiID(buxiID);
    }

    @Override
    public List<Pxstuclasstable> GetAllStuClassList(Long buxiID) {
        return iPxstuclasstableDao.GetAllStuClassList(buxiID);
    }

    @Override
    public int DeleteAllStuClassByBuxiID(Long buxiID) {
        return iPxstuclasstableDao.DeleteAllStuClassByBuxiID(buxiID);
    }

    @Override
    public Pxstuclasstable GetPxstuclassBybxIDAndclassID(Long classID, Long buxiID) {
        return iPxstuclasstableDao.GetPxstuclassBybxIDAndclassID(classID, buxiID);
    }

    @Override
    public int DeletestuclassByClassID(Long classID) {
        return iPxstuclasstableDao.DeletestuclassByClassID(classID);
    }
}

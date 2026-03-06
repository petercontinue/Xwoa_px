package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxclasstable;
import com.xwcloud.cloud.zsbm.Dao.IPxclasstableDao;
import com.xwcloud.cloud.zsbm.Service.IPxclasstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
@Service
public class PxclasstableServiceImpl extends ServiceImpl<IPxclasstableDao, Pxclasstable> implements IPxclasstableService {

    @Autowired
    IPxclasstableDao iPxclasstableDao;

    @Override
    public List<Pxclasstable> GetClassByClassName(String className) {
        return iPxclasstableDao.GetClassByClassName(className);
    }

    @Override
    public Pxclasstable GetClassByClassNameOne(String className) {
        return iPxclasstableDao.GetClassByClassNameOne(className);
    }

    @Override
    public List<searchVO> GetAllClassInfoList(long campusID,long qiyeID) {
        return iPxclasstableDao.GetAllClassInfoList(campusID,qiyeID);
    }

    @Override
    public List<Pxclasstable> GetClassByClassnameList(String className) {
        return iPxclasstableDao.GetClassByClassnameList(className);
    }
}

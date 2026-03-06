package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxclasstable;
import com.xwcloud.cloud.sys.Dao.IPxclasstableDao;
import com.xwcloud.cloud.sys.Service.IPxclasstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
@Service
public class PxclasstableServiceImpl extends ServiceImpl<IPxclasstableDao, Pxclasstable> implements IPxclasstableService {

    @Autowired
    IPxclasstableDao iPxclasstableDao;

    @Override
    public List<Pxclasstable> getNopkClass(Long qiyeID) {
        return iPxclasstableDao.getNopkClass(qiyeID);
    }

    @Override
    public List<HashMap<String, String>> getOnemonthnokeshiClass(Long qiyeID) {
        return iPxclasstableDao.getOnemonthnokeshiClass(qiyeID);
    }
}

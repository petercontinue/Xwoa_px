package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkeshizhuansongtable;
import com.xwcloud.cloud.zsbm.Dao.IPxkeshizhuansongtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxkeshizhuansongtableService;
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
public class PxkeshizhuansongtableServiceImpl extends ServiceImpl<IPxkeshizhuansongtableDao, Pxkeshizhuansongtable> implements IPxkeshizhuansongtableService {

    @Autowired
    IPxkeshizhuansongtableDao iPxkeshizhuansongtableDao;

    @Override
    public List<Pxkeshizhuansongtable> GetStuzhuansongInfo(Long songstuID, Long shoustuID) {
        return iPxkeshizhuansongtableDao.GetStuzhuansongInfo(songstuID, shoustuID);
    }
}

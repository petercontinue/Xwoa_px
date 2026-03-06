package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;

import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.pkxk.Dao.IPxsubjecttableDao;
import com.xwcloud.cloud.pkxk.Service.IPxsubjecttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
@Service
public class PxsubjecttableServiceImpl extends ServiceImpl<IPxsubjecttableDao, Pxsubjecttable> implements IPxsubjecttableService {
    @Autowired
    IPxsubjecttableDao iPxsubjecttableDao;

    @Override
    public List<searchVO> GetAllKemuList(long qiyeID) {
        return iPxsubjecttableDao.GetAllKemuList(qiyeID);
    }
}

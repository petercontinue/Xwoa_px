package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;
import com.xwcloud.cloud.model.pkxk.Vo.buxistyleVo;
import com.xwcloud.cloud.pkxk.Dao.IPxbuxistyletableDao;
import com.xwcloud.cloud.pkxk.Service.IPxbuxistyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-20
 */
@Service
public class PxbuxistyletableServiceImpl extends ServiceImpl<IPxbuxistyletableDao, Pxbuxistyletable> implements IPxbuxistyletableService {
	@Autowired
    IPxbuxistyletableDao iPxbuxistyletableDao;

    @Override
    public List<buxistyleVo> getbuxiStyleList(Long qiyeID) {
        return iPxbuxistyletableDao.getbuxiStyleList(qiyeID);
    }
}

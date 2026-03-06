package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstugradetable;

import com.xwcloud.cloud.model.pkxk.Vo.stugradeVo;
import com.xwcloud.cloud.pkxk.Dao.IPxstugradetableDao;
import com.xwcloud.cloud.pkxk.Service.IPxstugradetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-20
 */
@Service
public class PxstugradetableServiceImpl extends ServiceImpl<IPxstugradetableDao, Pxstugradetable> implements IPxstugradetableService {
	@Autowired
    IPxstugradetableDao iPxstugradetableDao;


    @Override
    public List<stugradeVo> getgradeList(Long qiyeID) {
        return iPxstugradetableDao.getgradeList(qiyeID);
    }
}

package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Pxkeshistuteachertable;
import com.xwcloud.cloud.pkxk.Dao.IPxkeshistuteachertableDao;
import com.xwcloud.cloud.pkxk.Service.IPxkeshistuteachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxkeshistuteachertableServiceImpl extends ServiceImpl<IPxkeshistuteachertableDao, Pxkeshistuteachertable> implements IPxkeshistuteachertableService {
	@Autowired
    IPxkeshistuteachertableDao iPxkeshistuteachertableDao;

    @Override
    public List<Pxkeshistuteachertable> selectstuTeakehao(QueryWrapper queryWrapper) {
        return iPxkeshistuteachertableDao.selectstuTeakehao(queryWrapper);
    }
}

package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.PxxiangcetableVo;
import com.xwcloud.cloud.model.entity.Pxxiangcetable;
import com.xwcloud.cloud.wsc.Dao.IPxxiangcetableDao;
import com.xwcloud.cloud.wsc.Service.IPxxiangcetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-13
 */
@Service
public class PxxiangcetableServiceImpl extends ServiceImpl<IPxxiangcetableDao, Pxxiangcetable> implements IPxxiangcetableService {

    @Autowired
    IPxxiangcetableDao iPxxiangcetableDao;

    @Override
    public Page<PxxiangcetableVo> getPage(Page page, Wrapper wrapper) {
        return iPxxiangcetableDao.getPage(page, wrapper);
    }
}

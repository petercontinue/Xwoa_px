package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.genjinInfoVo;
import com.xwcloud.cloud.model.Vo.genjinliushuiVo;
import com.xwcloud.cloud.model.entity.Pxyxgengjintable;
import com.xwcloud.cloud.zsbm.Dao.IPxyxgengjintableDao;
import com.xwcloud.cloud.zsbm.Service.IPxyxgengjintableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Service
public class PxyxgengjintableServiceImpl extends ServiceImpl<IPxyxgengjintableDao, Pxyxgengjintable> implements IPxyxgengjintableService {
    @Autowired
    IPxyxgengjintableDao iPxyxgengjintableDao;

    @Override
    public List<Pxyxgengjintable> GetAllYixiangGenjinByStuID(long stuID) {
        return iPxyxgengjintableDao.GetAllYixiangGenjinByStuID(stuID);
    }

    @Override
    public Page<genjinInfoVo> GetgenjinInfoPages(Page page, QueryWrapper queryWrapper) {
        return iPxyxgengjintableDao.GetgenjinInfoPages(page, queryWrapper);
    }


    @Override
    public int DeleteGenjinRecordsBystuID(long stuID) {
        return iPxyxgengjintableDao.DeleteGenjinRecordsBystuID(stuID);
    }

    @Override
    public Page<genjinliushuiVo> GegenjinLiushuiPages(Page page, QueryWrapper wrapper) {
        return iPxyxgengjintableDao.GegenjinLiushuiPages(page, wrapper);
    }

    @Override
    public List<genjinliushuiVo> GetExportGenjinliushui(QueryWrapper wrapper) {
        return iPxyxgengjintableDao.GetExportGenjinliushui(wrapper);
    }
}

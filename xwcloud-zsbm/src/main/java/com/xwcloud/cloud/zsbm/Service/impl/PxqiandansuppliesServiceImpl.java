package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.buyWpVo;
import com.xwcloud.cloud.model.Vo.shangpinliushuiVo;
import com.xwcloud.cloud.model.entity.Pxqiandansupplies;
import com.xwcloud.cloud.zsbm.Dao.IPxqiandansuppliesDao;
import com.xwcloud.cloud.zsbm.Service.IPxqiandansuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
@Service
public class PxqiandansuppliesServiceImpl extends ServiceImpl<IPxqiandansuppliesDao, Pxqiandansupplies> implements IPxqiandansuppliesService {

    @Autowired
    IPxqiandansuppliesDao iPxqiandansuppliesDao;

    @Override
    public Pxqiandansupplies GetQiandanSuppliesByqdIDandSupID(Long QiandaninfoID, Long TeachingSuppliesID) {
        return iPxqiandansuppliesDao.GetQiandanSuppliesByqdIDandSupID(QiandaninfoID, TeachingSuppliesID);
    }

    @Override
    public int deleteqiandansuppliesByQiandanID(Long QiandaninfoID) {
        return iPxqiandansuppliesDao.deleteqiandansuppliesByQiandanID(QiandaninfoID);
    }

    @Override
    public Page<shangpinliushuiVo> GetQiandanSuppliesPages(Page page, QueryWrapper wrapper) {
        return iPxqiandansuppliesDao.GetQiandanSuppliesPages(page, wrapper);
    }

    @Override
    public List<shangpinliushuiVo> GetQiandanSuppliesList(QueryWrapper wrapper) {
        return iPxqiandansuppliesDao.GetQiandanSuppliesList(wrapper);
    }

    @Override
    public List<buyWpVo> GetAllWupingList(long qiandanID) {
        return iPxqiandansuppliesDao.GetAllWupingList(qiandanID);
    }

    @Override
    public String getWP(Wrapper wrapper) {
        return iPxqiandansuppliesDao.getWP(wrapper);
    }
}

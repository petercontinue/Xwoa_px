package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxqiandansuppliesDao;
import com.xwcloud.cloud.caiwu.Service.IPxqiandansuppliesService;
import com.xwcloud.cloud.model.entity.Pxqiandansupplies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxqiandansuppliesServiceImpl extends ServiceImpl<IPxqiandansuppliesDao, Pxqiandansupplies> implements IPxqiandansuppliesService {
    @Autowired
    private IPxqiandansuppliesDao iPxqiandansuppliesDao;

    @Override
    public List<Pxqiandansupplies> tuisplist(Wrapper wrapper) {
        return iPxqiandansuppliesDao.tuisplist(wrapper);
    }

    @Override
    public String getBuysupplies(Wrapper wrapper) {
        return iPxqiandansuppliesDao.getBuysupplies(wrapper);
    }
}

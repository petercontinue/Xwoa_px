package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxpaiketableDao;
import com.xwcloud.cloud.homeschool.Service.IPxpaiketableService;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-28
 */
@Service
public class PxpaiketableServiceImpl extends ServiceImpl<IPxpaiketableDao, Pxpaiketable> implements IPxpaiketableService {

    @Override
    public List<HashMap<String, String>> getClassID(Wrapper wrapper) {
        return this.baseMapper.getClassID(wrapper);
    }
}

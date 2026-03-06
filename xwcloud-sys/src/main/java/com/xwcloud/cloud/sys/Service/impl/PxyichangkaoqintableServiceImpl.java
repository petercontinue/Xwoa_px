package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxyichangkaoqintable;

import com.xwcloud.cloud.model.Vo.staffyichangkaoqinVO;
import com.xwcloud.cloud.sys.Dao.IPxyichangkaoqintableDao;
import com.xwcloud.cloud.sys.Service.IPxyichangkaoqintableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-18
 */
@Service
public class PxyichangkaoqintableServiceImpl extends ServiceImpl<IPxyichangkaoqintableDao, Pxyichangkaoqintable> implements IPxyichangkaoqintableService {

    @Autowired
    IPxyichangkaoqintableDao iPxyichangkaoqintableDao;

    @Override
    public List<HashMap<String, String>> getyichangstaffbandCampusID(QueryWrapper queryWrapper) {
        return iPxyichangkaoqintableDao.getyichangstaffbandCampusID(queryWrapper);
    }

    @Override
    public Page<staffyichangkaoqinVO> GetyichangkaoqinPages(Page page, QueryWrapper wrapper) {
        return iPxyichangkaoqintableDao.GetyichangkaoqinPages(page, wrapper);
    }

    @Override
    public List<staffyichangkaoqinVO> getyichangkaoqingList(QueryWrapper wrapper) {
        return iPxyichangkaoqintableDao.getyichangkaoqingList(wrapper);
    }
}

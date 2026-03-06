package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.yxqiandanVo;
import com.xwcloud.cloud.model.entity.Pxyxqiandantable;
import com.xwcloud.cloud.zsbm.Dao.IPxyxqiandantableDao;
import com.xwcloud.cloud.zsbm.Service.IPxyxqiandantableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Service
public class PxyxqiandantableServiceImpl extends ServiceImpl<IPxyxqiandantableDao, Pxyxqiandantable> implements IPxyxqiandantableService {

    @Autowired
    IPxyxqiandantableDao iPxyxqiandantableDao;

    @Override
    public Page<yxqiandanVo> GetAllLiuyanyixiangqiandanPages(Page page, QueryWrapper wrapper) {
        return iPxyxqiandantableDao.GetAllLiuyanyixiangqiandanPages(page, wrapper);
    }

    @Override
    public List<yxqiandanVo> GetyixiangqiandanList(QueryWrapper wrapper) {
        return iPxyxqiandantableDao.GetyixiangqiandanList(wrapper);
    }
}

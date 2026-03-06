package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IPxkeshistutableDao;
import com.xwcloud.cloud.wsc.Service.IPxkeshistutableService;
import com.xwcloud.cloud.model.Vo.stuKehaoVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-05
 */
@Service
public class PxkeshistutableServiceImpl extends ServiceImpl<IPxkeshistutableDao, Pxkeshistutable> implements IPxkeshistutableService {
    @Autowired
    IPxkeshistutableDao iPxkeshistutableDao;

    @Override
    public Page<stuKehaoVo> getkeshiStu(Page page, QueryWrapper queryWrapper) {
        return iPxkeshistutableDao.getkeshiStu(page, queryWrapper);
    }
}

package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import com.xwcloud.cloud.stu.Dao.IPxstafftableDao;
import com.xwcloud.cloud.stu.Service.IPxstafftableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-29
 */
@Service
public class PxstafftableServiceImpl extends ServiceImpl<IPxstafftableDao, Pxstafftable> implements IPxstafftableService {
    @Autowired
    IPxstafftableDao iPxstafftableDao;

    @Override
    public List<listVo> GetBanzhurenFenpei(QueryWrapper queryWrapper) {
        return iPxstafftableDao.GetBanzhurenFenpei(queryWrapper);
    }
}

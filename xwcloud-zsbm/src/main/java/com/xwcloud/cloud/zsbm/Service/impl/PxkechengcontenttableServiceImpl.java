package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.kechengContentVo;
import com.xwcloud.cloud.model.entity.Pxkechengcontenttable;
import com.xwcloud.cloud.zsbm.Dao.IPxkechengcontenttableDao;
import com.xwcloud.cloud.zsbm.Service.IPxkechengcontenttableService;
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
public class PxkechengcontenttableServiceImpl extends ServiceImpl<IPxkechengcontenttableDao, Pxkechengcontenttable> implements IPxkechengcontenttableService {

    @Autowired
    IPxkechengcontenttableDao iPxkechengcontenttableDao;

    @Override
    public Page<kechengContentVo> getKechengContentPages(Page page, QueryWrapper wrapper) {
        return iPxkechengcontenttableDao.getKechengContentPages(page, wrapper);
    }

    @Override
    public List<Pxkechengcontenttable> getKechengcontentBykcidandpx(long kechengID, Integer contentPaixu) {
        return iPxkechengcontenttableDao.getKechengcontentBykcidandpx(kechengID, contentPaixu);
    }
}

package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.assetsVO;
import com.xwcloud.cloud.model.Vo.asstestyleVO;
import com.xwcloud.cloud.model.Vo.dengjiassetsVO;
import com.xwcloud.cloud.model.entity.Pxassetstable;
import com.xwcloud.cloud.sys.Dao.IPxassetstableDao;
import com.xwcloud.cloud.sys.Service.IPxassetstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
@Service
public class PxassetstableServiceImpl extends ServiceImpl<IPxassetstableDao, Pxassetstable> implements IPxassetstableService {

    @Autowired
    IPxassetstableDao iPxassetstableDao;

    @Override
    public Page<assetsVO> getassetsPages(Page page, QueryWrapper wrapper) {
        return iPxassetstableDao.getassetsPages(page, wrapper);
    }

    @Override
    public List<asstestyleVO> getAllasstestyleList(QueryWrapper wrapper) {
        return iPxassetstableDao.getAllasstestyleList(wrapper);
    }

    @Override
    public List<assetsVO> GetAllAssetsList(QueryWrapper queryWrapper) {
        return iPxassetstableDao.GetAllAssetsList(queryWrapper);
    }

    @Override
    public List<dengjiassetsVO> GetListAssetsDengjiList(QueryWrapper queryWrapper) {
        return iPxassetstableDao.GetListAssetsDengjiList(queryWrapper);
    }

    @Override
    public List<dengjiassetsVO> GetListAssetsBaofeiList(QueryWrapper queryWrapper) {
        return iPxassetstableDao.GetListAssetsBaofeiList(queryWrapper);
    }
}

package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.daikebiaoVO;
import com.xwcloud.cloud.model.Vo.stupaikeVO;
import com.xwcloud.cloud.model.Vo.teapaikeVO;
import com.xwcloud.cloud.model.Vo.xiaokeVO;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.wsc.Dao.IPxpaiketableDao;
import com.xwcloud.cloud.wsc.Service.IPxpaiketableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Service
public class PxpaiketableServiceImpl extends ServiceImpl<IPxpaiketableDao, Pxpaiketable> implements IPxpaiketableService {
	@Autowired
    IPxpaiketableDao iPxpaiketableDao;

    @Override
    public Page<stupaikeVO> GetStupaikePages(Page page, QueryWrapper wrapper) {
        return iPxpaiketableDao.GetStupaikePages(page, wrapper);
    }

    @Override
    public Page<teapaikeVO> GetTeacherPaikePages(Page page, QueryWrapper wrapper) {
        return iPxpaiketableDao.GetTeacherPaikePages(page, wrapper);
    }

    @Override
    public Page<daikebiaoVO> GetDayKebiaoPage(Page page, QueryWrapper wrapper) {
        return iPxpaiketableDao.GetDayKebiaoPage(page, wrapper);
    }

    @Override
    public Page<xiaokeVO> GetAllxiaokeDataPage(Page page, QueryWrapper wrapper) {
        return iPxpaiketableDao.GetAllxiaokeDataPage(page, wrapper);
    }
}

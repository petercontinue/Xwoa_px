package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.classstuinfoVO;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;

import com.xwcloud.cloud.model.Vo.ziyouqiandaoLookStuVo;
import com.xwcloud.cloud.pkxk.Dao.IPxstuclasstableDao;
import com.xwcloud.cloud.pkxk.Service.IPxstuclasstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxstuclasstableServiceImpl extends ServiceImpl<IPxstuclasstableDao, Pxstuclasstable> implements IPxstuclasstableService {
    @Autowired
    IPxstuclasstableDao iPxstuclasstableDao;

    @Override
    public Page<ziyouqiandaoLookStuVo> getziyouqiandaoLookStuPage(Page page, QueryWrapper queryWrapper) {
        return iPxstuclasstableDao.getziyouqiandaoLookStuPage(page, queryWrapper);
    }

    @Override
    public List<Pxstuclasstable> selectstuclass(QueryWrapper queryWrapper) {
        return iPxstuclasstableDao.selectstuclass(queryWrapper);
    }

    @Override
    public List<classstuinfoVO> getClassStuInfobyClassID(long classID, long qiyeID) {
        return iPxstuclasstableDao.getClassStuInfobyClassID(classID, qiyeID);
    }

    @Override
    public List<classstuinfoVO> getstuInfoBypaikeID(long paikeID, long qiyeID) {
        return iPxstuclasstableDao.getstuInfoBypaikeID(paikeID, qiyeID);
    }
}

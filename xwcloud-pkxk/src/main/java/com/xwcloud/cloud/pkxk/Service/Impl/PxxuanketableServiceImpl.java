package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.paikexiaoekestuVO;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.model.entity.Pxxuanketable;

import com.xwcloud.cloud.model.Vo.teacherIDVo;
import com.xwcloud.cloud.pkxk.Dao.IPxxuanketableDao;
import com.xwcloud.cloud.pkxk.Service.IPxxuanketableService;
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
public class PxxuanketableServiceImpl extends ServiceImpl<IPxxuanketableDao, Pxxuanketable> implements IPxxuanketableService {
    @Autowired
    IPxxuanketableDao iPxxuanketableDao;

    @Override
    public List<paikexiaoekestuVO> getpaikexiaoekestuList(QueryWrapper queryWrapper) {
        return iPxxuanketableDao.getpaikexiaoekestuList(queryWrapper);
    }

    @Override
    public List<Pxxuanketable> selectxuanke(QueryWrapper queryWrapper) {
        return iPxxuanketableDao.selectxuanke(queryWrapper);
    }

    @Override
    public List<teacherIDVo> gexkStuBypkID(Long paikeID) {
        return iPxxuanketableDao.gexkStuBypkID(paikeID);
    }

    @Override
    public List<Pxpaiketable> getNokehaoStutoPk(Long kechengID, Long stuID, Long qiyeID) {
        return iPxxuanketableDao.getNokehaoStutoPk(kechengID, stuID, qiyeID);
    }
}

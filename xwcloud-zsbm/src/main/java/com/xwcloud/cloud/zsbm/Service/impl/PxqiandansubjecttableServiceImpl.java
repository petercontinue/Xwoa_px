package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.buyKechengVo;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;

import com.xwcloud.cloud.zsbm.Dao.IPxqiandansubjecttableDao;
import com.xwcloud.cloud.zsbm.Service.IPxqiandansubjecttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class PxqiandansubjecttableServiceImpl extends ServiceImpl<IPxqiandansubjecttableDao, Pxqiandansubjecttable> implements IPxqiandansubjecttableService {

    @Autowired
    IPxqiandansubjecttableDao iPxqiandansubjecttableDao;


    @Override
    public BigDecimal GetQiandanKechengzongjia(Long qianDanInfoID) {
        return iPxqiandansubjecttableDao.GetQiandanKechengzongjia(qianDanInfoID);
    }

    @Override
    public Pxqiandansubjecttable GetQiandansubjectZidingyi(QueryWrapper wrapper) {
        return iPxqiandansubjecttableDao.GetQiandansubjectZidingyi(wrapper);
    }

    @Override
    public Integer DeleteQiandanSubjectByStuID(Long stuID, Long qiyeID) {
        return iPxqiandansubjecttableDao.DeleteQiandanSubjectByStuID(stuID, qiyeID);
    }

    @Override
    public List<Pxqiandansubjecttable> GetQiandanSubjectByQianDanID(Long qianDanInfoID) {
        return iPxqiandansubjecttableDao.GetQiandanSubjectByQianDanID(qianDanInfoID);
    }

    @Override
    public Integer DeleteQiandansubjectByQdID(Long qianDanInfoID, Long qiyeID) {
        return iPxqiandansubjecttableDao.DeleteQiandansubjectByQdID(qianDanInfoID, qiyeID);
    }

    @Override
    public List<buyKechengVo> GetQiandanKechengList(long qiandanID) {
        return iPxqiandansubjecttableDao.GetQiandanKechengList(qiandanID);
    }
}

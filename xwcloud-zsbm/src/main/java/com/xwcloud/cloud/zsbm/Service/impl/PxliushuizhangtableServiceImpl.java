package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.xinqianliushuiVo;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import com.xwcloud.cloud.zsbm.Dao.IPxliushuizhangtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxliushuizhangtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
@Service
public class PxliushuizhangtableServiceImpl extends ServiceImpl<IPxliushuizhangtableDao, Pxliushuizhangtable> implements IPxliushuizhangtableService {

    @Autowired
    IPxliushuizhangtableDao iPxliushuizhangtableDao;

    @Override
    public List<Pxliushuizhangtable> GetQiandanLiushuiList(Long qiandanID) {
        return iPxliushuizhangtableDao.GetQiandanLiushuiList(qiandanID);

    }

    @Override
    public Integer DeleteLiushuiByQiandanID(Long qiandanID, Long qiyeID) {
        return iPxliushuizhangtableDao.DeleteLiushuiByQiandanID(qiandanID, qiyeID);
    }

    @Override
    public Pxliushuizhangtable GetLiushuiByQdidAndPst(Long qiandanID, Long payMoneyStyle) {
        return iPxliushuizhangtableDao.GetLiushuiByQdidAndPst(qiandanID, payMoneyStyle);
    }

    @Override
    public Integer DeleteliushuiBystuID(Long stuID, Long qiyeID) {
        return iPxliushuizhangtableDao.DeleteliushuiBystuID(stuID, qiyeID);
    }

    @Override
    public Integer deleteLiushuiByStuIdAndqiandanID(Long stuID, Long qiandanID, Long qiyeID) {
        return iPxliushuizhangtableDao.deleteLiushuiByStuIdAndqiandanID(stuID, qiandanID, qiyeID);
    }

    @Override
    public List<xinqianliushuiVo> getqdliushuiList(QueryWrapper queryWrapper) {
        return iPxliushuizhangtableDao.getqdliushuiList(queryWrapper);
    }
}

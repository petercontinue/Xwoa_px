package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.qiandanstaffVO;
import com.xwcloud.cloud.model.entity.Pxqiandanstafftable;
import com.xwcloud.cloud.zsbm.Dao.IPxqiandanstafftableDao;
import com.xwcloud.cloud.zsbm.Service.IPxqiandanstafftableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class PxqiandanstafftableServiceImpl extends ServiceImpl<IPxqiandanstafftableDao, Pxqiandanstafftable> implements IPxqiandanstafftableService {

    @Autowired
    IPxqiandanstafftableDao iPxqiandanstafftableDao;

    @Override
    public List<Pxqiandanstafftable> GetQiandanStaffByQiandanID(Long qiandanID) {
        return iPxqiandanstafftableDao.GetQiandanStaffByQiandanID(qiandanID);
    }

    @Override
    public int dleteQiandanStaffbyQiandanID(Long qiandanID) {
        return iPxqiandanstafftableDao.dleteQiandanStaffbyQiandanID(qiandanID);
    }

    @Override
    public List<qiandanstaffVO> GetqiandanStaffList(long qiandanID) {
        return iPxqiandanstafftableDao.GetqiandanStaffList(qiandanID);
    }

    @Override
    public List<HashMap<String, Object>> getyejitrentoPayweikuan(QueryWrapper queryWrapper) {
        return iPxqiandanstafftableDao.getyejitrentoPayweikuan(queryWrapper);
    }
}

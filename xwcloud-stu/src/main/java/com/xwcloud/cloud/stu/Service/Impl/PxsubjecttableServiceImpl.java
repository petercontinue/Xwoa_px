package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
import com.xwcloud.cloud.stu.Dao.IPxsubjecttableDao;
import com.xwcloud.cloud.stu.Service.IPxsubjecttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
@Service
public class PxsubjecttableServiceImpl extends ServiceImpl<IPxsubjecttableDao, Pxsubjecttable> implements IPxsubjecttableService {
    @Autowired
    IPxsubjecttableDao iPxsubjecttableDao;

    @Override
    public List<listVo> GetcampusIDkemu(Long campusID, Long qiyeID) {
        return iPxsubjecttableDao.GetcampusIDkemu(campusID, qiyeID);
    }

    @Override
    public List<Pxsubjecttable> selectsub(QueryWrapper queryWrapper) {
        return iPxsubjecttableDao.selectsub(queryWrapper);
    }

    @Override
    public List<listVo> getallkemuName(Long qiyeID) {
        return iPxsubjecttableDao.getallkemuName(qiyeID);
    }
}

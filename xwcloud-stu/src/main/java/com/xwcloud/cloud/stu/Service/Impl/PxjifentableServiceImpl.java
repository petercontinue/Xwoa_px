package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.stuIntegralVo;
import com.xwcloud.cloud.model.entity.Pxjifentable;
import com.xwcloud.cloud.stu.Dao.IPxjifentableDao;
import com.xwcloud.cloud.stu.Service.IPxjifentableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-19
 */
@Service
public class PxjifentableServiceImpl extends ServiceImpl<IPxjifentableDao, Pxjifentable> implements IPxjifentableService {
    @Autowired
    IPxjifentableDao iPxjifentableDao;

    @Override
    public List<stuIntegralVo> ExportIntegral(QueryWrapper queryWrapper) {
        return iPxjifentableDao.ExportIntegral(queryWrapper);
    }

    @Override
    public List<Pxjifentable> getJF(Long stuID, Long qiyeID) {
        return iPxjifentableDao.getJF(stuID, qiyeID);
    }
}

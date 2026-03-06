package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxtesttypetable;

import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.stu.Dao.IPxtesttypetableDao;
import com.xwcloud.cloud.stu.Service.IPxtesttypetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-22
 */
@Service
public class PxtesttypetableServiceImpl extends ServiceImpl<IPxtesttypetableDao, Pxtesttypetable> implements IPxtesttypetableService {
    @Autowired
    IPxtesttypetableDao iPxtesttypetableDao;

    @Override
    public List<listVo> getTesttype(Long qiyeID) {
        return iPxtesttypetableDao.getTesttype(qiyeID);
    }

    @Override
    public Pxtesttypetable getOnetest(Long qiyeID) {
        return iPxtesttypetableDao.getOnetest(qiyeID);
    }

    @Override
    public List<Pxtesttypetable> selecttesttype(QueryWrapper queryWrapper) {
        return iPxtesttypetableDao.selecttesttype(queryWrapper);
    }

}

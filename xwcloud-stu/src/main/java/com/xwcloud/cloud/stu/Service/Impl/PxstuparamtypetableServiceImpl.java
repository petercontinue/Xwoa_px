package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstuparamtypetable;

import com.xwcloud.cloud.model.Vo.pramaTypeVo;
import com.xwcloud.cloud.stu.Dao.IPxstuparamtypetableDao;
import com.xwcloud.cloud.stu.Service.IPxstuparamtypetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-08
 */
@Service
public class PxstuparamtypetableServiceImpl extends ServiceImpl<IPxstuparamtypetableDao, Pxstuparamtypetable> implements IPxstuparamtypetableService {
    @Autowired
    IPxstuparamtypetableDao iPxstuparamtypetableDao;

    @Override
    public List<Pxstuparamtypetable> selectstuparamtype(QueryWrapper queryWrapper) {
        return iPxstuparamtypetableDao.selectstuparamtype(queryWrapper);
    }

    @Override
    public List<pramaTypeVo> getByqiye(Long stuID, Long qiyeID) {
        return iPxstuparamtypetableDao.getByqiye(stuID, qiyeID);
    }

    @Override
    public List<pramaTypeVo> getOne(Long qiyeID) {
        return iPxstuparamtypetableDao.getOne(qiyeID);
    }
}

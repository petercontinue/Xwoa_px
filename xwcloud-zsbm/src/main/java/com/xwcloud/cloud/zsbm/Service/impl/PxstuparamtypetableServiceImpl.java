package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.pramaTypeVo;
import com.xwcloud.cloud.model.Vo.stuparamtypeVO;
import com.xwcloud.cloud.model.entity.Pxstuparamtypetable;
import com.xwcloud.cloud.zsbm.Dao.IPxstuparamtypetableDao;
import com.xwcloud.cloud.zsbm.Service.IPxstuparamtypetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-14
 */
@Service
public class PxstuparamtypetableServiceImpl extends ServiceImpl<IPxstuparamtypetableDao, Pxstuparamtypetable> implements IPxstuparamtypetableService {

    @Autowired
    IPxstuparamtypetableDao iPxstuparamtypetableDao;

    @Override
    public List<stuparamtypeVO> GetStuparamtypeList(long qiyeID) {
        return iPxstuparamtypetableDao.GetStuparamtypeList(qiyeID);
    }

    @Override
    public List<pramaTypeVo> getOne(Long qiyeID) {
        return iPxstuparamtypetableDao.getOne(qiyeID);
    }

    @Override
    public List<Pxstuparamtypetable> selectstuparamtype(QueryWrapper queryWrapper) {
        return iPxstuparamtypetableDao.selectstuparamtype(queryWrapper);
    }
}

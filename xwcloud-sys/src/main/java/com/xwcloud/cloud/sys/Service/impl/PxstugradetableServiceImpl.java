package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstugradetable;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.sys.Dao.IPxstugradetableDao;
import com.xwcloud.cloud.sys.Service.IPxstugradetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-21
 */
@Service
//@DS("#header.DBname")
public class PxstugradetableServiceImpl extends ServiceImpl<IPxstugradetableDao, Pxstugradetable> implements IPxstugradetableService {
    @Autowired
    IPxstugradetableDao iPxstugradetableDao;

    @Override
    public List<Pxstugradetable> GetStuGradeListByqiyeIDAndGrade(Long qiyeID, String stuGradeName) {
        return iPxstugradetableDao.GetStuGradeListByqiyeIDAndGrade(qiyeID, stuGradeName);
    }

    @Override
    public List<Pxstutable> GetstuByqiyeIDAndgradeID(Long qiyeID, String stuGradeID) {
        return iPxstugradetableDao.GetstuByqiyeIDAndgradeID(qiyeID, stuGradeID);
    }
}

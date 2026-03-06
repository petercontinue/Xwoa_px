package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import com.xwcloud.cloud.zsbm.Dao.IPxstafftableDao;
import com.xwcloud.cloud.zsbm.Service.IPxstafftableService;
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
 * @since 2021-05-11
 */
@Service
public class PxstafftableServiceImpl extends ServiceImpl<IPxstafftableDao, Pxstafftable> implements IPxstafftableService {
    @Autowired
    IPxstafftableDao iPxstafftableDao;

    @Override
    public List<HashMap<String, Object>> getCampusTostaff(QueryWrapper queryWrapper) {
        return iPxstafftableDao.getCampusTostaff(queryWrapper);
    }

    @Override
    public List<Pxstafftable> getSubjectTeacher(Long subjectID, Long qiyeID) {
        return iPxstafftableDao.getSubjectTeacher(subjectID, qiyeID);
    }
}

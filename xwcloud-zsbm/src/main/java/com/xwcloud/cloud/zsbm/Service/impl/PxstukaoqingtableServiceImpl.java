package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;
import com.xwcloud.cloud.zsbm.Dao.IPxstukaoqingtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxstukaoqingtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
@Service
public class PxstukaoqingtableServiceImpl extends ServiceImpl<IPxstukaoqingtableDao, Pxstukaoqingtable> implements IPxstukaoqingtableService {

    @Autowired
    IPxstukaoqingtableDao iPxstukaoqingtableDao;

    @Override
    public List<Pxstukaoqingtable> GetStuKaoqing(String haveclassDate, String startClassDateTime, String endClassDateTime, Long classID, String teacherNames) {
        return iPxstukaoqingtableDao.GetStuKaoqing(haveclassDate, startClassDateTime, endClassDateTime, classID, teacherNames);
    }

    @Override
    public List<Pxstukaoqingtable> GetKaoqingByStuID(Long stuID) {
        return iPxstukaoqingtableDao.GetKaoqingByStuID(stuID);
    }

    @Override
    public int deleteStuKaoqingByStuID(Long stuID) {
        return iPxstukaoqingtableDao.deleteStuKaoqingByStuID(stuID);
    }

}

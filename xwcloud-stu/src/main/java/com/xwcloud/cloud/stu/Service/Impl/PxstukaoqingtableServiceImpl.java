package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.countykqVo;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;
import com.xwcloud.cloud.stu.Dao.IPxstukaoqingtableDao;
import com.xwcloud.cloud.stu.Service.IPxstukaoqingtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxstukaoqingtableServiceImpl extends ServiceImpl<IPxstukaoqingtableDao, Pxstukaoqingtable> implements IPxstukaoqingtableService {
    @Autowired
    IPxstukaoqingtableDao iPxstukaoqingtableDao;

    @Override
    public List<Pxstukaoqingtable> allStukaoqing(Long stuID, Long qiyeID) {
        return iPxstukaoqingtableDao.allStukaoqing(stuID, qiyeID);
    }

    @Override
    public countykqVo ykqCount(Date haveclassDate, Time startClassDateTime, Time endClassDateTime, Long classId, String teacherIDs, Long qiyeID) {
        return iPxstukaoqingtableDao.ykqCount(haveclassDate, startClassDateTime, endClassDateTime, classId, teacherIDs, qiyeID);
    }

    @Override
    public List<Pxstukaoqingtable> getykq(Date haveclassDate, Time startClassDateTime, Time endClassDateTime, Long classID, String teacherIDs, Long qiyeID) {
        return iPxstukaoqingtableDao.getykq(haveclassDate, startClassDateTime, endClassDateTime, classID, teacherIDs, qiyeID);
    }
}

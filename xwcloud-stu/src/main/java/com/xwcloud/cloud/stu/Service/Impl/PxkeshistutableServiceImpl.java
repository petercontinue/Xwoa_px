package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.stu.Dao.IPxkeshistutableDao;
import com.xwcloud.cloud.stu.Service.IPxkeshistutableService;
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
 * @since 2020-11-22
 */
@Service
public class PxkeshistutableServiceImpl extends ServiceImpl<IPxkeshistutableDao, Pxkeshistutable> implements IPxkeshistutableService {
    @Autowired
    IPxkeshistutableDao iPxkeshistutableDao;

    //获取学员课时
    @Override
    public List<Pxkeshistutable> getkeshistu(Long stuID, Long qiyeID) {
        return iPxkeshistutableDao.getkeshistu(stuID, qiyeID);
    }

    //获取
    @Override
    public List<Pxkeshistutable> otherStuks(Long classID, Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime, Long stuID, Long qiyeID) {
        return iPxkeshistutableDao.otherStuks(classID, haveClassDate, startLessonDateTime, endLessonDateTime, stuID, qiyeID);
    }

    @Override
    public List<Pxkeshistutable> getBybxID(Long bxkcID, Long qiyeID) {
        return iPxkeshistutableDao.getBybxID(bxkcID, qiyeID);
    }

    @Override
    public List<Pxkeshistutable> getByStuKcDateTime(Long stuID, Long kechengID, Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime, Long qiyeID) {
        return iPxkeshistutableDao.getByStuKcDateTime(stuID, kechengID, haveClassDate, startLessonDateTime, endLessonDateTime, qiyeID);
    }

    @Override
    public List<Pxkeshistutable> getByStuKc(Long stuID, Long kechengID, Long qiyeID) {
        return iPxkeshistutableDao.getByStuKc(stuID, kechengID, qiyeID);
    }
}

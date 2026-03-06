package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkeshiteachertable;
import com.xwcloud.cloud.stu.Dao.IPxkeshiteachertableDao;
import com.xwcloud.cloud.stu.Service.IPxkeshiteachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-22
 */
@Service
public class PxkeshiteachertableServiceImpl extends ServiceImpl<IPxkeshiteachertableDao, Pxkeshiteachertable> implements IPxkeshiteachertableService {

    @Autowired
    IPxkeshiteachertableDao iPxkeshiteachertableDao;

    @Override
    public List<Pxkeshiteachertable> Teachks(Long classID, Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime,Long qiyeID) {
        return iPxkeshiteachertableDao.Teachks(classID, haveClassDate, startLessonDateTime, endLessonDateTime,qiyeID);
    }
}

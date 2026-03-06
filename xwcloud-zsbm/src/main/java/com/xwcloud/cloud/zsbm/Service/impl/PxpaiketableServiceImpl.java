package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.pkchongtuVo;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.zsbm.Dao.IPxpaiketableDao;
import com.xwcloud.cloud.zsbm.Service.IPxpaiketableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
@Service
public class PxpaiketableServiceImpl extends ServiceImpl<IPxpaiketableDao, Pxpaiketable> implements IPxpaiketableService {

    @Autowired
    IPxpaiketableDao iPxpaiketableDao;

    @Override
    public List<Pxpaiketable> GetPaikebyClassID(Long classID) {
        return iPxpaiketableDao.GetPaikebyClassID(classID);
    }

    @Override
    public List<Pxpaiketable> GetAllPaikeByClassID(Long classID) {
        return iPxpaiketableDao.GetAllPaikeByClassID(classID);
    }



    @Override
    public int DeletePaikeByClassID(Long classID) {
        return iPxpaiketableDao.DeletePaikeByClassID(classID);
    }

    @Override
    public List<Pxpaiketable> GetPaikeListbyClassID(Long classID, Date haveClassDate) {
        return iPxpaiketableDao.GetPaikeListbyClassID(classID, haveClassDate);
    }

    @Override
    public List<Pxpaiketable> GetPaikeByclassroomIDandDate(Long classRooID, Date haveClassDate) {
        return iPxpaiketableDao.GetPaikeByclassroomIDandDate(classRooID, haveClassDate);
    }

    @Override
    public List<pkchongtuVo> GetKechengChongtuList(Long teacherID, Date haveClassDate) {
        return iPxpaiketableDao.GetKechengChongtuList(teacherID, haveClassDate);
    }

    @Override
    public List<Pxpaiketable> getTeacherTimeCT(QueryWrapper<Pxpaiketable> wrapper) {
        return iPxpaiketableDao.getTeacherTimeCT(wrapper);
    }
}

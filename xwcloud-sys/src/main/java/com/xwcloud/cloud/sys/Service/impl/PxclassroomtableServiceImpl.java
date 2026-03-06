package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.classroomVo;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.sys.Dao.IPxclassroomtableDao;
import com.xwcloud.cloud.sys.Service.IPxclassroomtableService;
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
public class PxclassroomtableServiceImpl extends ServiceImpl<IPxclassroomtableDao, Pxclassroomtable> implements IPxclassroomtableService {

    @Autowired
    IPxclassroomtableDao iPxclassroomtableDao;

    @Override
    public Page<classroomVo> GetClassRoomPage(Page page, QueryWrapper wrapper) {
        return iPxclassroomtableDao.GetClassRoomPage(page, wrapper);
    }

    @Override
    public List<Pxpaiketable> GetpaikeByclassRoomid(String classRoomID) {
        return iPxclassroomtableDao.GetpaikeByclassRoomid(classRoomID);
    }

    @Override
    public List<Pxclassroomtable> GetClassRoomListByqiyeID_CampusID_classRoomName(Long qiyeID, Long campusID, String classRoomName) {
        return iPxclassroomtableDao.GetClassRoomListByqiyeID_CampusID_classRoomName_Dao(qiyeID, campusID, classRoomName);
    }
}

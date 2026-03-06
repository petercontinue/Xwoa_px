package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.ExportStuRoomVo;
import com.xwcloud.cloud.model.Vo.RoomManaVo;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxroomtable;
import com.xwcloud.cloud.stu.Dao.IPxroomtableDao;
import com.xwcloud.cloud.stu.Service.IPxroomtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Service
public class PxroomtableServiceImpl extends ServiceImpl<IPxroomtableDao, Pxroomtable> implements IPxroomtableService {

    @Autowired
    IPxroomtableDao iPxroomtableDao;

    @Override
    public List<listVo> getOkList(Long qiyeID) {
        return iPxroomtableDao.getOkList(qiyeID);
    }

    //导出学员住宿
    @Override
    public List<ExportStuRoomVo> exportRoom(QueryWrapper queryWrapper) {
        return iPxroomtableDao.exportStuRoom(queryWrapper);
    }

    //添加时去重
    @Override
    public List<Pxroomtable> getAdd(Long campusID, String number, Long qiyeID) {
        return iPxroomtableDao.getAdd(campusID, number, qiyeID);
    }

    //修改宿舍管理时去重
    @Override
    public List<Pxroomtable> getRoomUp(Long campusID, String number, Long id, Long qiyeID) {
        return iPxroomtableDao.getRoomUp(campusID, number, id, qiyeID);
    }

    //宿舍管理分页查询
    @Override
    public Page<RoomManaVo> getRmManagePage(Page page, QueryWrapper queryWrapper) {
        return iPxroomtableDao.getRmManagePage(page, queryWrapper);
    }
}

package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxroomtableDao;
import com.xwcloud.cloud.homeschool.Service.IPxroomtableService;

import com.xwcloud.cloud.model.Vo.ExportStuRoomVo;
import com.xwcloud.cloud.model.Vo.RoomManaVo;
import com.xwcloud.cloud.model.entity.Pxroomtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Service
public class PxroomtableServiceImpl extends ServiceImpl<IPxroomtableDao, Pxroomtable> implements IPxroomtableService {

    @Autowired
    IPxroomtableDao iPxroomtableDao;
    //导出学员住宿
    @Override
    public List<ExportStuRoomVo> exportRoom(QueryWrapper queryWrapper) {
        return iPxroomtableDao.exportStuRoom(queryWrapper);
    }

    //添加时去重
    @Override
    public List<Pxroomtable> getAdd(String campusID, String number) {
        return iPxroomtableDao.getAdd(campusID,number);
    }
    //修改宿舍管理时去重
    @Override
    public List<Pxroomtable> getRoomUp(String campusID, String number,String id) {
        return iPxroomtableDao.getRoomUp(campusID,number,id);
    }
    //宿舍管理分页查询
    @Override
    public Page<RoomManaVo> getRmManagePage(Page page, QueryWrapper queryWrapper) {
        return iPxroomtableDao.getRmManagePage(page,queryWrapper);
    }
}

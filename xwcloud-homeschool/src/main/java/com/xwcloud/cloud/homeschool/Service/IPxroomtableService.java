package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.ExportStuRoomVo;
import com.xwcloud.cloud.model.Vo.RoomManaVo;
import com.xwcloud.cloud.model.entity.Pxroomtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxroomtableService extends IService<Pxroomtable> {
    List<ExportStuRoomVo> exportRoom(QueryWrapper queryWrapper);
    List<Pxroomtable> getAdd(String campusID,String number);
    List<Pxroomtable> getRoomUp(String campusID,String number,String id);
    Page<RoomManaVo> getRmManagePage(Page page,QueryWrapper queryWrapper);
}

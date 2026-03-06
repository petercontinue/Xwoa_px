package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.classroomVo;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;
import com.xwcloud.cloud.model.entity.Pxpaiketable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-21
 */
public interface IPxclassroomtableService extends IService<Pxclassroomtable> {

    public Page<classroomVo> GetClassRoomPage(Page page, QueryWrapper wrapper);

    List<Pxpaiketable> GetpaikeByclassRoomid(String classRoomID);

    List<Pxclassroomtable> GetClassRoomListByqiyeID_CampusID_classRoomName(Long qiyeID, Long campusID, String classRoomName);
}

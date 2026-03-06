package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.pkchongtuVo;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */

public interface IPxpaiketableService extends IService<Pxpaiketable> {
    List<Pxpaiketable> GetPaikebyClassID(Long classID);

    List<Pxpaiketable> GetAllPaikeByClassID(Long classID);

    int DeletePaikeByClassID(Long classID);

    List<Pxpaiketable> GetPaikeListbyClassID(Long classID, Date haveClassDate);

    List<Pxpaiketable> GetPaikeByclassroomIDandDate(Long classRooID, Date haveClassDate);

    List<pkchongtuVo> GetKechengChongtuList(Long teacherID, Date haveClassDate);

    List<Pxpaiketable> getTeacherTimeCT(QueryWrapper<Pxpaiketable> wrapper);
}

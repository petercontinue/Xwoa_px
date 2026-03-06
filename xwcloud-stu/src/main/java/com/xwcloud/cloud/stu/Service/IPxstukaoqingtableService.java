package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.countykqVo;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxstukaoqingtableService extends IService<Pxstukaoqingtable> {
    List<Pxstukaoqingtable> allStukaoqing(Long stuID,Long qiyeID);
    countykqVo ykqCount(Date haveclassDate, Time startClassDateTime, Time endClassDateTime, Long classId, String teacherIDs, Long qiyeID);
    List<Pxstukaoqingtable> getykq(Date haveclassDate, Time startClassDateTime, Time endClassDateTime, Long classID, String teacherIDs, Long qiyeID);
}

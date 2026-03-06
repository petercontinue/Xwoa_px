package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-22
 */
public interface IPxkeshistutableService extends IService<Pxkeshistutable> {
    List<Pxkeshistutable> getkeshistu(Long stuID,Long qiyeID);
    List<Pxkeshistutable> otherStuks(Long classID, Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime, Long stuID,Long qiyeID);
    List<Pxkeshistutable> getBybxID(Long bxkcID,Long qiyeID);
    List<Pxkeshistutable> getByStuKcDateTime( Long stuID,Long kechengID,Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime,Long qiyeID);
    List<Pxkeshistutable> getByStuKc(Long stuID,Long kechengID,Long qiyeID);
}

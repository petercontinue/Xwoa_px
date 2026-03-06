package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshiteachertable;

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
public interface IPxkeshiteachertableService extends IService<Pxkeshiteachertable> {
    List<Pxkeshiteachertable> Teachks(Long classID, Date haveClassDate, Time startLessonDateTime, Time endLessonDateTime,Long qiyeID);
}

package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
public interface IPxstukaoqingtableService extends IService<Pxstukaoqingtable> {
    List<Pxstukaoqingtable> GetStuKaoqing(String haveclassDate, String startClassDateTime, String endClassDateTime, Long classID, String teacherNames);

    List<Pxstukaoqingtable> GetKaoqingByStuID(Long stuID);

    int deleteStuKaoqingByStuID(Long stuID);
}

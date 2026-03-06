package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshistuteachertable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxkeshistuteachertableService extends IService<Pxkeshistuteachertable> {
    List<Pxkeshistuteachertable> getksStuTeachs(Long keshiStuTableID,Long qiyeID);
}

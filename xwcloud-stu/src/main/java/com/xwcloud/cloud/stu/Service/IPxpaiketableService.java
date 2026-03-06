package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxpaiketable;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxpaiketableService extends IService<Pxpaiketable> {
    List<Pxpaiketable> getTk(Long classID, Date hvdate, Long qiyeID);

    List<Pxpaiketable> getpkBYClassID(Long classID, Long qiyeID);

    List<Pxpaiketable> getBykq(Long classID, Long qiyeID);

    List<Pxpaiketable> getBykc(Long kechengID, Long qiyeID);
}

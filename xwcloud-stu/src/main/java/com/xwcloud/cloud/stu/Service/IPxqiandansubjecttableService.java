package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxqiandansubjecttableService extends IService<Pxqiandansubjecttable> {
    List<Pxqiandansubjecttable> getqdSubject(Long stuID,Long qiyeID);
    List<Pxqiandansubjecttable> getdelzsQdSub(int kcSty,Long stuID, Long kechengID, BigDecimal buykeshiNum,Long qiyeID);
}

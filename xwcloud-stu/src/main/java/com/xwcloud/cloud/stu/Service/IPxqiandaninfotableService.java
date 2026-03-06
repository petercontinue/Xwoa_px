package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;

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
public interface IPxqiandaninfotableService extends IService<Pxqiandaninfotable> {
    List<Pxqiandaninfotable> getQD(Long stuID,Long qiyeID);
    List<Pxqiandaninfotable> getdelzsQD(Long stuID, BigDecimal sMoney,Long qiyeID);
}

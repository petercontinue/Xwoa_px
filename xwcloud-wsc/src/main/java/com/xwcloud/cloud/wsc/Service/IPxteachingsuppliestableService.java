package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliestable;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
public interface IPxteachingsuppliestableService extends IService<Pxteachingsuppliestable> {
    int UpdateteachingsuppliesKucun(Long ID, BigDecimal kucun,Long qiyeID);
}
